# Common
Custom_box Common(自定义匣子-公共组件)

描述：实现通用公共组件全局异常处理、统一消息返回、基类、BeanSwitch、Swagger、TokenFilter、SecurityFilter、Authorization（授权）、Utils

### 架构
Common在整个项目比重：
todo 架构图

### 实现功能
1. 全局统一响应返回
2. 全局异常处理
3. TokenUtil
4. ApplicationUtil
5. 部分基类实现（BaseBean）

### 未实现功能
1. TokenFilter
2. SecurityFilter
2. SecurityContextHolder
3. LoggingAspect 打印格式及记录
4. 基类（Controller、beanSwitch、service）
5. Utils

预计额外需实现PathFilter 判读是否放行（ThreadLocal实现）