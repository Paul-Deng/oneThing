package fund.paul.crypto.domain.business.future.service.impl;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import fund.paul.common.basic.PageParamsDTO;
import fund.paul.common.constant.Constants;
import fund.paul.common.service.impl.SuperServiceImpl;
import fund.paul.crypto.domain.business.future.pojo.FuturePO;
import fund.paul.crypto.domain.business.future.service.IFutureService;
import fund.paul.crypto.manufacture.mapper.FutureMapper;
import fund.paul.cryptoapi.pojo.OrderRequestParams;
import fund.paul.db.utils.CustomLambdaQueryWrapper;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

/**
 * @author paul
 * @date 2025/1/15 13:12
 */
@Service
public class FutureServiceImpl extends SuperServiceImpl<FutureMapper, FuturePO> implements IFutureService {

    public List<FuturePO> list(OrderRequestParams orderRequestParams) {
        return super.list(getPageDTO(orderRequestParams), getQueryWrapper(orderRequestParams));
    }

    @Override
    public Long count(OrderRequestParams orderRequestParams) {
        return super.count(getQueryWrapper(orderRequestParams));
    }

    private CustomLambdaQueryWrapper<FuturePO> getQueryWrapper(OrderRequestParams orderRequestParams) {
        CustomLambdaQueryWrapper<FuturePO> wrapper = new CustomLambdaQueryWrapper<>();
        if (orderRequestParams == null) {
            return wrapper;
        }
        if (!CollectionUtils.isEmpty(orderRequestParams.getOrderIdCol())) {
            wrapper.in(FuturePO::getId, orderRequestParams.getOrderIdCol());
        }
        if (!CollectionUtils.isEmpty(orderRequestParams.getCoinKeyCol())) {
            wrapper.in(FuturePO::getCoinKey, orderRequestParams.getOrderIdCol());
        }
        return wrapper;
    }

    private PageDTO<FuturePO> getPageDTO(PageParamsDTO pageRequest) {
        PageDTO<FuturePO> pageDTO = new PageDTO<>();
        pageDTO.setOrders(Collections.singletonList(OrderItem.desc(Constants.Col.CREATED_TIME)));
        if (pageRequest == null) {
            return pageDTO;
        }
        if (!CollectionUtils.isEmpty(pageRequest.getOrderBy())) {
            List<String> orderList = pageRequest.getOrderBy();
            Set<String> colSet = Arrays.stream(FuturePO.class.getFields())
                    .map(field -> field.getName().toLowerCase(Locale.ROOT)).collect(Collectors.toSet());
            Iterator<String> orderIt = orderList.iterator();
            while (orderIt.hasNext()) {
                String next = orderIt.next().toLowerCase(Locale.ROOT);
                if (!colSet.contains(next)) {
                    orderIt.remove();
                }
            }
            pageDTO.setOrders((OrderItem.descs(orderList.toArray(new String[0]))));
        }
        if (!ObjectUtils.isEmpty(pageRequest.getPage())) {
            pageDTO.setCurrent(pageRequest.getPage());
        }
        if (!ObjectUtils.isEmpty(pageRequest.getSize())) {
            pageDTO.setSize(pageRequest.getSize().getSize());
        }
        return pageDTO;
    }
}
