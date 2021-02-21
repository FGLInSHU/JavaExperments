local lock_key = ARG[1]
local lock_val = ARG[2]

if redis.call("set", lock_key, lock_val, "NX PX 100000") == true
then
    return 0
else
end
