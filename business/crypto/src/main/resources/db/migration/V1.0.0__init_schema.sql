-- 合约表
CREATE TABLE cryptoservicedb.tbl_order_future
(
    id            BIGINT      NOT NULL PRIMARY KEY, -- 合约id
    coinKey       VARCHAR(50) NOT NULL,             -- 币值对
    avgPrice      NUMERIC,                          -- 描述
    totalAmount   NUMERIC,                          -- 描述
    slPrice       NUMERIC,                          -- 描述
    maxEntryPrice NUMERIC,                          -- 描述
    minEntryPrice NUMERIC,                          -- 描述
    tpPriceList   VARCHAR(50),                      -- 描述
    profit        NUMERIC,                          -- 描述
    count         NUMERIC,                          -- 描述
    deleted       SMALLINT DEFAULT 0,               -- 逻辑删除标志：0-未删除；1-已删除
    status        VARCHAR(50) NOT NULL,             -- 合约状态
    parentId      BIGINT      NOT NULL,             -- 父订单id
    createdTime   BIGINT      NOT NULL,             -- 创建时间
    updatedTime   BIGINT      NOT NULL,             -- 更新时间
    updatedBy     BIGINT      NOT NULL,             -- 被谁更新
    createdBy     BIGINT      NOT NULL              -- 被谁创建
);