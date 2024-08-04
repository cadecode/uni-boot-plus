# Code Specification

> 本项目指 uni-boot-plus 项目
>
>此文档描述本项目代码规约

## 常见命名规范

| 含义描述             | 命名             | 包名          |
|------------------|----------------|-------------|
| web 接口层          | xxxController  | controller  |
| service 接口       | xxxService     | service     |
| service 实现       | xxxServiceImpl | serviceImpl |
| dao 数据访问层        | xxxMapper      | mapper      |
| manager 复杂业务聚合处理 | xxxManager     | manager     |
| feign 客户端        | xxxClient      | feignclient |
| bean 转换器         | xxxConvert     | convert     |
| aop 切面           | xxxAspect      | aspect      |
| 常量               | xxxConst       | consts      |
| 枚举类              | xxxEnum        | enums       |

## 实体类命名参考

| 义描述         | 命名       | 包名                    |
|-------------|----------|-----------------------|
| 数据库实体       | xxx      | bean.entity / bean.po |
| vo 展示层实体    | xxxVo    | bean.vo               |
| dto 数据传输层实体 | xxxDto   | bean.dto              |
| bo 复杂业务聚合实体 | xxxBo    | bean.bo               |
| mongo 实体    | xxxDoc   | bean.doc              |
| cache 缓存实体  | xxxCache | bean.cache            |
| es 实体       | xxxIndex | bean.index            |

接口返回实体一般使用 `Vo` 结尾，如分页查询系统用户接口返回类型 `SysUserPageVo`

接口接收实体一般使用 `Dto` 结尾，如分页查询系统用户接口参数类型 `SysUserPageDto`

在微服务架构下，服务间传递对象一般使用 `Dto / Bo` 结尾

消息、事件相关的实体类，一般使用 `Msg / Event` 结尾

另外，一些独立于业务之外的实体，由于工具类、框架配置等的需要，可放于 model 包，也可使用内部类方式

## 工具类命名参考

工具类放于 util 包

一般使用单数的 `Util` 结尾，当工具类需要注入容器后使用时，推荐以 `Kit` 结尾进行区分
