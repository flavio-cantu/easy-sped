package jakarta.persistence;

/**
 * Utilizar o ManyToOne para carregamentos lazy.
 * Essa classe existe para inibir o uso dela mesma no projeto
 * <p>
 * Atenção!
 * Fique atento, o cascade do ManyToOne não funciona se não for uma lista.
 * Mas se for pra escolher entre facilidade no desenvolvimento e performace nas consultas
 * a resposta é óbvia. Usuário em primeiro lugar.
 */
@Deprecated
public @interface OneToOne {
}
