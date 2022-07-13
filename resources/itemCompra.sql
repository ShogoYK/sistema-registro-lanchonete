tcreate table itemCompra(
idCompra int not null,
codIngrediente int not null,
quant int not null,
valor double,
foreign key(idCompra) references compra(idCompra),
foreign key(codIngrediente) references ingrediente(codIngrediente)
)