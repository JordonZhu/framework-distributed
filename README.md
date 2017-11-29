# framework-distributed

# 1.切面拦截使用了自定义注解的方法，获取参数，拿到basevo的uuid,通过redis的setnx做幂等处理。[Idempotent]
# 2.controller 的出入参日志打印切面。[LogAspect]
