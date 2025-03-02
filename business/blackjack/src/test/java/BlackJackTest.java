import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 *
 * @author paul
 * @date 2025/2/21 01:32
 */
public class BlackJackTest {
    private static final int[] INIT_DECK = {24, 24, 24, 24, 24, 24, 24, 24, 24, 96}; // A,2-9,10
    private Map<String, BigDecimal[]> memo;

    public BlackJackTest() {
        memo = new HashMap<>();
    }

    // 生成状态键
    private String getKey(int sumNp, int numAces, int numCards, int[] deck) {
        StringBuilder sb = new StringBuilder();
        sb.append(sumNp).append(",").append(numAces).append(",").append(numCards).append(",");
        for (int count : deck) {
            sb.append(count).append(",");
        }
        return sb.toString();
    }

    // 计算当前点数
    private int calculateTotal(int sumNp, int numAces) {
        int total = sumNp + numAces;
        for (int i = 0; i < numAces; i++) {
            if (total + 10 <= 21) {
                total += 10;
            } else {
                break;
            }
        }
        return total;
    }

    // 判断软17
    private boolean isSoft17(int sumNp, int numAces, int numCards) {
        return numCards == 2 && numAces == 1 && sumNp == 6;
    }

    /**
     * 问题背景
     * 在澳门21点中，使用6副标准扑克牌（每副52张，去掉大小王），总共312张牌。目标是计算庄家和闲家手牌点数在17到21之间（含爆牌）的概率分布。规则如下：
     * 牌堆组成：
     * A：24张（6副 × 4张/副）
     * 2-9：每种点数24张（6副 × 4张/副）
     * 10（包括10、J、Q、K）：96张（6副 × 16张/副）
     * 点数计算：
     * A可计为1或11，取不爆牌情况下最大值。
     * 2-10按面值计算，J、Q、K计为10。
     * 庄家规则：
     * 如果手牌为A + 6（软17，两张牌），必须继续要牌。
     * 其他情况下，点数 >= 17 时停止要牌。
     * 闲家规则：
     * 点数 >= 17 或点数 == 21 时停止要牌。
     * 我们将使用动态规划计算从初始手牌开始，经过抽牌后，最终点数为17到21或爆牌的概率。
     * 计算原理
     * 1. 状态定义
     * 为了精确计算概率，动态规划需要记录以下状态：
     * sumNp：手牌中非A牌的点数总和。
     * numAces：手牌中A的数量。
     * numCards：当前手牌张数（用于判断软17）。
     * deck：牌堆中每种点数（A, 2, ..., 10）的剩余数量。
     * 2. 软17判断
     * 软17仅在庄家手牌为两张牌且为A + 6时成立，即numCards == 2 && numAces == 1 && sumNp == 6。
     * 3. 停止条件
     * 闲家：
     * 点数 >= 17 或点数 == 21，停止要牌。
     * 庄家：
     * 如果是软17（A + 6），继续要牌。
     * 否则，点数 >= 17 时停止要牌。
     * 4. 点数计算
     * 定义函数 calculateTotal(sumNp, numAces)：
     * 初始值为 sumNp + numAces（所有A计为1）。
     * 逐个尝试将A计为11，若总和 <= 21，则加上10，否则保持不变。
     * 5. 动态规划转移
     * 继续要牌：
     * 从牌堆中抽取一张牌（点数1-10）。
     * 更新状态：若抽到A，则numAces加1；否则sumNp加上抽到的点数。
     * 计算子问题的概率，并按抽牌概率加权。
     * 停止要牌：
     * 若满足停止条件，记录当前点数的概率（17-21或爆牌）。
     * 6. 概率表示
     * 使用数组 prob[6] 表示结果：
     * prob[0-4]：点数17-21的概率。
     * prob[5]：爆牌（>21）的概率。
     * 7. 记忆化
     * 用哈希表记录状态 (sumNp, numAces, numCards, deck) 的概率分布，避免重复计算。
     *
     * @param sumNp
     * @param numAces
     * @param numCards
     * @param deck
     * @param isDealer
     * @return
     */
    // 动态规划计算概率
    private BigDecimal[] dp(int sumNp, int numAces, int numCards, int[] deck, boolean isDealer) {
        String key = getKey(sumNp, numAces, numCards, deck);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        BigDecimal[] prob = new BigDecimal[6]; // 17-21, bust
        Arrays.fill(prob, BigDecimal.ZERO.setScale(6));
        int total = calculateTotal(sumNp, numAces);

        if (total > 21) {
            prob[5] = BigDecimal.ONE.setScale(6); // 爆牌
        } else if (total == 21) {
            prob[4] = BigDecimal.ONE.setScale(6); // 21, 停止
//        } else if (!isDealer && total >= 17) {
//            prob[total - 17] = 1.0; // 闲家 >= 17 停止
        } else if (isDealer && total >= 17 && !isSoft17(sumNp, numAces, numCards)) {
            prob[total - 17] = BigDecimal.ONE.setScale(6); // 庄家硬17或以上停止
        } else {
            int totalCards = Arrays.stream(deck).sum();
            if (totalCards == 0) {
                prob[5] = BigDecimal.ONE.setScale(6); // 无牌可抽，爆牌
            } else {
                for (int v = 1; v <= 10; v++) {
                    int deckIndex = v - 1;
                    if (deck[deckIndex] > 0) {
                        int[] newDeck = deck.clone();
                        newDeck[deckIndex]--;
                        int newSumNp = (v == 1) ? sumNp : sumNp + v;
                        int newNumAces = (v == 1) ? numAces + 1 : numAces;
                        int newNumCards = numCards + 1;
                        BigDecimal p = BigDecimal.valueOf(deck[deckIndex]).setScale(6).divide(BigDecimal.valueOf(totalCards), RoundingMode.HALF_DOWN);
                        BigDecimal[] subProb = dp(newSumNp, newNumAces, newNumCards, newDeck, isDealer);
                        for (int i = 0; i < 6; i++) {
                            prob[i] = p.multiply(subProb[i]).add(prob[i]);
                        }
                    }
                }
            }
        }
        memo.put(key, prob);
        return prob;
    }

    @Test
    public void test() {
        BlackJackTest solver = new BlackJackTest();

        // 示例：闲家初始手牌 A + 2
        INIT_DECK[0]--;
        INIT_DECK[0]--; // 庄家手牌
        INIT_DECK[1]--;
        INIT_DECK[1]--;
        INIT_DECK[1]--;
        INIT_DECK[1]--;
        INIT_DECK[1]--;
        INIT_DECK[1]--;
        INIT_DECK[1]--;
        INIT_DECK[1]--;
        INIT_DECK[1]--;
        int[] playerDeck = INIT_DECK.clone();
        BigDecimal[] playerProbs = solver.dp(18, 1, 10, playerDeck, false);
        System.out.println("闲家 (A) 的概率分布:");
        for (int i = 0; i < 5; i++) {
            System.out.printf("P(%d): %.6f\n", i + 17, playerProbs[i]);
        }
        System.out.printf("P(爆牌): %.6f\n", playerProbs[5]);

        // 示例：庄家软17 (A + 6)
        int[] deck = INIT_DECK.clone();
        BigDecimal[] dealerProbs = solver.dp(6, 0, 1, deck, true);
        System.out.println("庄家A的概率分布:");
        for (int i = 0; i < 5; i++) {
            System.out.printf("P(%d): %.6f\n", i + 17, dealerProbs[i]);
        }
        System.out.printf("P(爆牌): %.6f\n", dealerProbs[5]);
    }
}
