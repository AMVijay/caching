# Redis Server

## Installation
Redis server can be installed in Linux using below command:
* `sudo apt install redis-server` - Install the server.
* `sudo service redis-server restart` - Start the redis service.
* Once redis-server started, using `redis-cli -h <ipaddress> -p <port number>` to connect to redis server.
* In the redis-cli connected shell, execute below commands for its respective output and purpose:
  * `PING` - would help us to know whether redis server is running or not. Response would be `PONG`.
  * `DBSIZE` would help to know the number of objects in redis server. Output would be the number of keys inside redis server.
  * `FLUSHALL` Flush entire redis cache server database.
  * `KEYS *` - To list all the key inside redis server.
