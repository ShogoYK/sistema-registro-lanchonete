create table compra(

idCompra int not null auto_increment primary key,
dataCompra date,
valorTotal Double
)

or

create table compra(

idCompra int not null auto_increment primary key,
dataCompra date,
valorTotal Double,
idFornecedor int,
FOREIGN KEY (idFornecedor) references fornecedor(idFornecedor)
)


