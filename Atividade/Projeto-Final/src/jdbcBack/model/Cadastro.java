package jdbcBack.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Cadastro {
    private int id;
    private String funcionario;
    private LocalDate registro;
    private String cargo;

    public Cadastro() {
    }

    public Cadastro(String funcionario, LocalDate registro, String cargo) {
        this.funcionario = funcionario;
        this.registro = registro;
        this.cargo = cargo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }

    public LocalDate getRegistro() {
        return registro;
    }

    public void setRegistro(LocalDate registro) {
        this.registro = registro;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    // Conversão para SQL e para Java
    public static String converterParaSQL(LocalDate data) {
        return data.toString(); // LocalDate já está no formato yyyy-MM-dd
    }

    public static String converterParaJava(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter); // Converte LocalDate para formato dd/MM/yyyy
    }
}
