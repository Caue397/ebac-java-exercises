package dev.cauegallizzi;

import dev.cauegallizzi.annotation.Table;
import dev.cauegallizzi.entity.Address;
import dev.cauegallizzi.table.User;
import dev.cauegallizzi.table.Workspace;

import java.lang.annotation.Annotation;

public class Main {
    static void main(String[] args) throws Exception {
        Class<?>[] clazzes = {User.class, Workspace.class, Address.class};

        for (Class<?> clazz : clazzes) {
            try {
                Annotation table = clazz.getAnnotation(Table.class);
                if (table == null) continue;
                String tableName = (String) table.annotationType().getMethod("value").invoke(table);
                System.out.println("Tabela: " + tableName);
            } catch (NoSuchMethodException _) {
            } catch (IllegalAccessException e) {
                System.out.println("O metodo value nao existe");
            } catch (Exception e) {
                throw new Exception("Aconteceu um erro inesperado");
            }
        }
    }
}
