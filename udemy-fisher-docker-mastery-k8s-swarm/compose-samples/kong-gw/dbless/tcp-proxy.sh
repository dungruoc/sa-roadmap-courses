curl -i -X POST http://localhost:8001/routes \
  --data "protocols[]=tcp" \
  --data "destinations[1].ip=0.0.0.0" \
  --data "destinations[1].port=31010" \
  --data "service.name=dremio-odbc"

 
curl -i -X POST http://localhost:8001/routes \
  --data "protocols[]=tcp" \
  --data "destinations[1].port=31010" \
  --data "service.name=dremio-odbc"


curl -i -X PATCH http://localhost:8001/services/dremio-odbc \
  --data "read_timeout= 2147483646" \
  --data "connect_timeout= 2147483646" \
  --data "write_timeout=2147483646"