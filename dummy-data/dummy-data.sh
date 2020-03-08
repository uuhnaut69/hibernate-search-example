echo "GENERATE CATALOG DUMMY DATA"
curl -X POST localhost:8080/catalogs

echo "GENERATE PRODUCT DUMMY DATA"
curl -X POST localhost:8080/products
