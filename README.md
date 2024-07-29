# IncomeClient Spring Boot Starter

## Описание

`IncomeClient` — это кастомный стартер для Spring Boot, предоставляющий функциональность для получения годового дохода пользователя из удалённого сервиса. Стартер использует `RestTemplate` для выполнения HTTP-запросов и обработки JSON-ответов.

## Особенности

- Конфигурация через свойства в файле `application.properties` или `application.yml`
- Обработка ошибок HTTP-запросов
- Автоматическая настройка `RestTemplate` и `IncomeClient`

## Зависимости

Проект использует следующие зависимости:

- `spring-boot-starter` — базовый стартер для Spring Boot.
- `spring-boot-starter-web` — стартер для веб-приложений.
- `spring-boot-autoconfigure` — автоматическая конфигурация Spring Boot.
- `json` — библиотека для работы с JSON.

## Использование

1. **Добавьте зависимость в `pom.xml`:**

   ```xml
   <dependency>
       <groupId>com.belaquaa</groupId>
       <artifactId>starter</artifactId>
       <version>1.0.0</version>
   </dependency>

2. **Настройте свойства в application.properties или application.yml:**
   income.url=http://example.com/income
   income.client.enabled=true

4. **Используйте IncomeClient в вашем коде:**
   ```
   @Autowired
   private IncomeClient incomeClient;

   public void someMethod() {
     double annualIncome = incomeClient.getAnnualIncome(userId);
     System.out.println("Annual income: " + annualIncome);
   }
   ```
