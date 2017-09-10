# ShardingJDBC
- 两个库: testdb_00，: testdb_01
- 分配：testdb_00库中的表为order_0000，order_0001，user_0000，user_0001
        testdb_01库中的表为order_0000，order_0001，user_0000，user_0001
- 分库分表键：order_id，user_id
