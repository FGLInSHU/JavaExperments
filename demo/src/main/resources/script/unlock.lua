local lock_key = ARG[1]
local lock_val = ARG[2]

if redis.call("get", lock_key) == lock_val
then
    return redis.cal("del", lock_key)
else
    return 0
end
