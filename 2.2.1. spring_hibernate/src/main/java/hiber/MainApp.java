package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User ivan = new User("Ivan", "Ivanov", "ivan@mail.ru");
      User petr = new User("Petr", "Petrov", "petr@mail.ru");
      User andrey = new User("Andrey", "Andreev", "andrey@mail.ru");
      User alexandr = new User("Alexandr", "Alexandrov", "alexandrov@mail.ru");

      Car muscovite = new Car("Muscovite", 23);
      Car lada = new Car("Lada", 11);
      Car volga = new Car("Volga", 14);
      Car kopeck = new Car("Kopeck", 8);

      ivan.setCar(muscovite);
      petr.setCar(lada);
      andrey.setCar(volga);
      alexandr.setCar(kopeck);

      userService.add(ivan);
      userService.add(petr);
      userService.add(andrey);
      userService.add(alexandr);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user.getCar());
      }

      System.out.println(userService.getUserByCar("Volga", 14));

      context.close();
   }
}
