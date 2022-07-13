create table itemVenda(

idVenda int not null,
codLanche int not null,
quant int not null,
valor double,
foreign key(idVenda) references venda(id),
foreign key(codLanche) references lanche(codLanche)
)