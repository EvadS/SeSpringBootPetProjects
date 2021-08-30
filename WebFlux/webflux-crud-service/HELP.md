# Getting Started



Mono<T> — is a publisher that produces from 0 to 1 value of T
Flux<T> — is a publisher that produces from 0 to N values of T

HTTP
Method	API Name	Path	Response
Status Code
POST	Create Catalogue Item	/	201
(Created)
GET	Get Catalogue Items	/	200
(Ok)
GET	Get Catalogue Item	/{sku}	200
(Ok)
PUT	Update Catalogue Item	/{sku}	200
(Ok)
DELETE	Delete Catalogue Item	/{sku}	204
(No Content)
POST	Upload Catalog Item Picture	/{sku}/image	201
(Created)