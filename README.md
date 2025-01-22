# Программное средство управления базой клиентов и контактов с формированием модуля аналитики и генерации отчетов (на примере туристического агентства)
# Архитектура
## Нотация C4
Контейнерный уровень архитектуры в нотации C4

![C4 2уровень](https://github.com/user-attachments/assets/94cdbfde-0793-4427-b3e7-e934406dabae)

Компонентный уровень архитектуры в нотации C4

![С4 3уровень](https://github.com/user-attachments/assets/beb6c2ad-4df7-458a-94ea-d088ba07e670)

## Структурные диаграммы 
Диаграмма классов программного средства

![д классов](https://github.com/user-attachments/assets/220fc3a2-4fd5-4b87-89ff-92caf222c638)

Диаграмма развертывания программного средства

![image](https://github.com/user-attachments/assets/f95a4246-a331-435f-9246-755f8bc9f161)

## Поведенческие диаграммы
Диаграмма деятельности процесса смены статусов "Контакт-Клиент"

![image](https://github.com/user-attachments/assets/9a77ab33-d35b-4ee0-8eaa-80194a910e8e)


Диаграмма последовательности процесса управления базой клиентов и контактов

![image](https://github.com/user-attachments/assets/c7182014-6249-441b-b51c-0a19a135ad76)

Диаграмма состояний объекта "Статус клиента"

![image](https://github.com/user-attachments/assets/71dc8304-07eb-472a-851b-73138b99aa5a)
## Схема данных 
ER-модель данных программного средства

![image](https://github.com/user-attachments/assets/feba0cf3-1910-4bbf-82df-9f204feab3f1)

# Пользовательский интерфейс
UI Kit для программного средства

![image](https://github.com/user-attachments/assets/5a486059-a31a-4e61-9db9-f329bad59bc5)

## User-flow диаграммы
User-flow диаграмма программного средства (Менеджер)

![image](https://github.com/user-attachments/assets/c4ef7aa0-7b83-45ae-bcfe-bf777e235bae)

User-flow диаграмма программного средства (Управляющий)

![image](https://github.com/user-attachments/assets/6cca5ef2-f89a-4563-8dcd-40561056767e)

## Примеры экранов UI
Главная информационная страница программного средства

![image](https://github.com/user-attachments/assets/2f6bd848-0230-45ee-9332-aa70231ca272)

Страница просмотров туров

![image](https://github.com/user-attachments/assets/b8a840c4-7361-4102-a9e5-416d99820e96)

Страница авторизации

![image](https://github.com/user-attachments/assets/1efa76b5-2edb-4b8e-bc93-09c8f0e8bb5c)

Страница регистрации

![image](https://github.com/user-attachments/assets/81804b99-ae43-4d25-8c9d-a5016e5d8d94)

Страница добавления туров
 
![image](https://github.com/user-attachments/assets/549d644d-be9b-46a8-b936-e7d68e4ed77e)

Форма заполнения информации о туре

![image](https://github.com/user-attachments/assets/9d238856-f7b1-4c02-b137-d3044af8262f) 

Карточка тура

![image](https://github.com/user-attachments/assets/a1ff6eb2-9f9d-4ebf-bba2-ab7f4c09f350)
 
Страница просмотров и добавления клиентов

![image](https://github.com/user-attachments/assets/f63d0e86-a39a-4cbf-a6d6-1603c96d579f) 

Форма заполнения карточки клиента

![image](https://github.com/user-attachments/assets/d74c7837-7c75-491e-95df-f0f6506790c8)

Форма добавления нового клиента

![image](https://github.com/user-attachments/assets/d0db6d09-492e-4bd0-9b92-f3aa8c519b75)

Страница управления аккаунтами пользователей

![image](https://github.com/user-attachments/assets/1ddcba62-81fc-436f-8802-e6494d310fc6)

Страница с аналитической информацией

![image](https://github.com/user-attachments/assets/e5b9f1fe-27c2-49bb-9943-3ae357409a47)

![image](https://github.com/user-attachments/assets/912d4e1b-fe86-435c-8dfe-b9888e89446f)

![image](https://github.com/user-attachments/assets/2c0c173b-2a53-4242-a1d7-f8db4117deab)

Вид документа после формирования отчета

![image](https://github.com/user-attachments/assets/8eb89796-9b4c-424a-a7fd-b390240cfac6)

# Документация
![image](https://github.com/user-attachments/assets/77dc3bf6-0195-4cc3-abf8-4619eaca36c4)

![image](https://github.com/user-attachments/assets/ab318ca2-4818-47ec-8847-b0c9cc2f490e)

![image](https://github.com/user-attachments/assets/8a14dad2-5264-451d-80c0-2e4dedf56c92)

![image](https://github.com/user-attachments/assets/ddcd0766-7fe6-464a-b55c-1156983960e6)

Полную версию документации к API можно посмотреть по ссылке: https://drive.google.com/file/d/1ptvjl_TW46IZWvJKYRIrc5Wgt1o0FITQ/view?usp=drive_link

# Оценка качества кода

![image](https://github.com/user-attachments/assets/ff71238a-81b7-4aa4-9edc-b07c59bf2d05)


# Тестирование

## Интеграционный тест:

@Test
void findAllSuccess() {
    given(repository.findAll()).willReturn(tours);

    List<Tour> findAll = service.findAllForTest();

    assertThat(findAll.size()).isEqualTo(tours.size());

    verify(repository, times(1)).findAll();
}

В тесте findAllSuccess() происходит несколько шагов, которые проверяют корректность работы сервиса, использующего репозиторий для получения данных.

![image](https://github.com/user-attachments/assets/a6d1fb69-c124-4745-92d7-a4fd45a70557)

## Модульный тест:

@Test
void saveSuccess() {
    Client save = clients.get(0);

    given(repository.save(save)).willReturn(save);

    Client saved = service.saveForTest(save);

    assertThat(saved.getFio()).isEqualTo(save.getFio());
    assertThat(saved.getTel()).isEqualTo(save.getTel());
    assertThat(saved.getPassport()).isEqualTo(save.getPassport());
    assertThat(saved.getDescription()).isEqualTo(save.getDescription());
    assertThat(saved.getCategory()).isEqualTo(save.getCategory());
    assertThat(saved.getGender()).isEqualTo(save.getGender());
    assertThat(saved.getTourType()).isEqualTo(save.getTourType());

    verify(repository, times(1)).save(save);
}

В тесте проверяется логика работы метода saveForTest() сервиса ClientService.

![image](https://github.com/user-attachments/assets/9d17f1a3-0cf2-461c-9850-8130f577ddb8)

# Безопасность
Пример кода из AuthorizationController:
Метод PostLogin:

public async Task<IActionResult> PostLogin()
        {
            using var reader = new StreamReader(Request.Body);
            var body = await reader.ReadToEndAsync();
            var authorization = JsonConvert.DeserializeObject<Authorization>(body);

            if (authorization == null)
            {
                return BadRequest();
            }

            //var (a,b) = HashPassword.GetHashPassword(authorization.Password);

            string sql = @"
                SELECT 
                    u.employee_id,
                    u.username,
                    u.password_hash,
                    u.salt,
                    r.role_name
                FROM 
                    Users u
                JOIN 
                    Roles r ON u.role_id = r.role_id
                WHERE 
                    u.username = @username;";

            using var connection = new NpgsqlConnection(DataBase.connString);
            connection.Open();
            using (var command = new NpgsqlCommand(sql, connection))
            {
                command.Parameters.AddWithValue("@username", authorization.Login);

                using (var readerSQL = command.ExecuteReader())
                {
                    if (readerSQL.Read())
                    {
                        string passwordHash = readerSQL.GetString(2);
                        byte[] salt = (byte[])readerSQL[3];

                        if (HashPassword.VerifyPassword(authorization.Password, passwordHash, salt))
                        {
                            int employee_id = readerSQL.GetInt32(0);
                            string userName = readerSQL.GetString(1);
                            string roleName = readerSQL.GetString(4);
                            var сookiesManager = new CookiesManager();
                            сookiesManager.GenerateTokenAndSet(Response, userName, roleName, employee_id);
                            return Ok();
                        }

                    }
                }
            }
            return Unauthorized();
        }

Метод LogOut:

[HttpDelete(Name = "LogOut")]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status401Unauthorized)]
        public async Task<IActionResult> LogOut()
        {
            var сookiesManager = new CookiesManager();

            if (!сookiesManager.CheckTokenValidity(Request, CookiesManager.Roles.GetAll()))
            {
                return Unauthorized();
            }

            CookiesManager.DeleteAllCookies(Response);

            return Ok();
        }
        
Реализация определения ролей в классе TokenValidetator:

public struct Roles
{
    public const string ADMIN = "Admin";
    public const string MANAGER = " MANAGER";
    

    public static string[] GetAll()
    {
        string[] res = { ADMIN, MANAGER };

        return res;
    }
}

Шифрование паролей: пароли пользователей хранятся в зашифрованном виде. Это повышает защиту в случае утечки данных:

private static string GetHashPassword(string password, byte[] salt)
{
    using (var pbkdf2 = new Rfc2898DeriveBytes(password, salt, 100000, HashAlgorithmName.SHA256))
    {
        byte[] hash = pbkdf2.GetBytes(32); // Длина хеша 32 байта (256 бит)
        return Convert.ToBase64String(hash);
    }
}



