JPA - составной ключ с @IdClass, включающий отношение @OneToOne (производная идентификация)


В этом примере @IdClass мы увидим, как использовать поле идентификатора целевого класса (ссылочный класс) отношения @OneToOne.
 Поле тождественности целевого класса называется «производным полем тождества».
 
 в этом подходе мы явно указываем аннотацию @Id для каждого поля которое 
 в последствии будет составным первичным ключём 