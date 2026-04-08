--liquibase formatted sql

--changeset dev:1 context:initial-data
INSERT INTO hangman_words (word, category, difficulty) VALUES
-- ANIMALS
-- EASY (3-5)
('кот', 'ANIMALS', 1), ('пес', 'ANIMALS', 1), ('лев', 'ANIMALS', 1), ('тигр', 'ANIMALS', 1), ('волк', 'ANIMALS', 1),
('заяц', 'ANIMALS', 1), ('слон', 'ANIMALS', 1), ('бык', 'ANIMALS', 1), ('лось', 'ANIMALS', 1), ('рысь', 'ANIMALS', 1),
('акула', 'ANIMALS', 1), ('кабан', 'ANIMALS', 1),

-- NORMAL (6-8)
('собака', 'ANIMALS', 2), ('медведь', 'ANIMALS', 2), ('жирафа', 'ANIMALS', 2), ('обезьяна', 'ANIMALS', 2), ('дельфин', 'ANIMALS', 2),
('тигрица', 'ANIMALS', 2), ('пантера', 'ANIMALS', 2), ('лисица', 'ANIMALS', 2), ('горилла', 'ANIMALS', 2), ('пингвин', 'ANIMALS', 2),
('фламинго', 'ANIMALS', 2), ('крокодил', 'ANIMALS', 2),

-- HARD (9-15)
('крокодилица', 'ANIMALS', 3), ('бегемотиха', 'ANIMALS', 3), ('четвероногое', 'ANIMALS', 3), ('млекопитающее', 'ANIMALS', 3), ('насекомоядное', 'ANIMALS', 3),
('земноводное', 'ANIMALS', 3), ('пресмыкающееся', 'ANIMALS', 3), ('сухопутный', 'ANIMALS', 3), ('плотоядное', 'ANIMALS', 3), ('травоядное', 'ANIMALS', 3),

-- FOOD
-- EASY (3-5)
('суп', 'FOOD', 1), ('сок', 'FOOD', 1), ('чай', 'FOOD', 1), ('хлеб', 'FOOD', 1), ('сыр', 'FOOD', 1),
('рис', 'FOOD', 1), ('торт', 'FOOD', 1), ('пицца', 'FOOD', 1), ('салат', 'FOOD', 1), ('борщ', 'FOOD', 1),
('паста', 'FOOD', 1), ('суши', 'FOOD', 1), ('блины', 'FOOD', 1), ('рагу', 'FOOD', 1), ('зефир', 'FOOD', 1),

-- NORMAL (6-8)
('котлета', 'FOOD', 2), ('оливье', 'FOOD', 2), ('пельмени', 'FOOD', 2), ('вареники', 'FOOD', 2), ('окрошка', 'FOOD', 2),
('шашлык', 'FOOD', 2), ('спагетти', 'FOOD', 2), ('круассан', 'FOOD', 2), ('шоколад', 'FOOD', 2), ('печенье', 'FOOD', 2),
('мармелад', 'FOOD', 2), ('пастила', 'FOOD', 2), ('варенье', 'FOOD', 2), ('сырник', 'FOOD', 2), ('пирожок', 'FOOD', 2),

-- HARD (9-15)
('гамбургер', 'FOOD', 3), ('мороженое', 'FOOD', 3), ('шоколадка', 'FOOD', 3), ('конфетница', 'FOOD', 3), ('бутерброд', 'FOOD', 3),
('мармеладки', 'FOOD', 3), ('шоколадки', 'FOOD', 3), ('мороженка', 'FOOD', 3), ('пирожные', 'FOOD', 3), ('маскарпоне', 'FOOD', 3),

-- COUNTRIES
-- EASY (3-5)
('куба', 'COUNTRIES', 1), ('перу', 'COUNTRIES', 1), ('чили', 'COUNTRIES', 1), ('ирак', 'COUNTRIES', 1), ('оман', 'COUNTRIES', 1),
('мали', 'COUNTRIES', 1), ('йемен', 'COUNTRIES', 1), ('того', 'COUNTRIES', 1), ('фиджи', 'COUNTRIES', 1), ('самоа', 'COUNTRIES', 1),

-- NORMAL (6-8)
('франция', 'COUNTRIES', 2), ('германия', 'COUNTRIES', 2), ('италия', 'COUNTRIES', 2), ('испания', 'COUNTRIES', 2), ('канада', 'COUNTRIES', 2),
('мексика', 'COUNTRIES', 2), ('египет', 'COUNTRIES', 2), ('турция', 'COUNTRIES', 2), ('греция', 'COUNTRIES', 2), ('швеция', 'COUNTRIES', 2),
('колумбия', 'COUNTRIES', 2), ('малайзия', 'COUNTRIES', 2), ('таиланд', 'COUNTRIES', 2),

-- HARD (9-15)
('аргентина', 'COUNTRIES', 3), ('венесуэла', 'COUNTRIES', 3), ('португалия', 'COUNTRIES', 3), ('швейцария', 'COUNTRIES', 3), ('австралия', 'COUNTRIES', 3),
('индонезия', 'COUNTRIES', 3), ('филиппины', 'COUNTRIES', 3), ('македония', 'COUNTRIES', 3), ('мадагаскар', 'COUNTRIES', 3), ('мавритания', 'COUNTRIES', 3),

-- PROFESSIONS
-- EASY (3-5)
('врач', 'PROFESSIONS', 1), ('повар', 'PROFESSIONS', 1), ('юрист', 'PROFESSIONS', 1), ('актер', 'PROFESSIONS', 1), ('певец', 'PROFESSIONS', 1),
('пилот', 'PROFESSIONS', 1), ('судья', 'PROFESSIONS', 1), ('няня', 'PROFESSIONS', 1), ('химик', 'PROFESSIONS', 1), ('физик', 'PROFESSIONS', 1),

-- NORMAL (6-8)
('боксер', 'PROFESSIONS', 2), ('фермер', 'PROFESSIONS', 2), ('инженер', 'PROFESSIONS', 2), ('учитель', 'PROFESSIONS', 2), ('водитель', 'PROFESSIONS', 2),
('продавец', 'PROFESSIONS', 2), ('менеджер', 'PROFESSIONS', 2), ('дизайнер', 'PROFESSIONS', 2), ('фотограф', 'PROFESSIONS', 2), ('повариха', 'PROFESSIONS', 2),
('астроном', 'PROFESSIONS', 2), ('геолог', 'PROFESSIONS', 2), ('биолог', 'PROFESSIONS', 2),

-- HARD (9-15)
('архитектор', 'PROFESSIONS', 3), ('стоматолог', 'PROFESSIONS', 3), ('кардиолог', 'PROFESSIONS', 3), ('математик', 'PROFESSIONS', 3), ('экономист', 'PROFESSIONS', 3),
('бухгалтер', 'PROFESSIONS', 3), ('парикмахер', 'PROFESSIONS', 3), ('космонавт', 'PROFESSIONS', 3), ('программист', 'PROFESSIONS', 3), ('преподаватель', 'PROFESSIONS', 3),

-- SPORTS
-- EASY (3-5)
('бокс', 'SPORTS', 1), ('бег', 'SPORTS', 1), ('гольф', 'SPORTS', 1), ('регби', 'SPORTS', 1), ('дзюдо', 'SPORTS', 1),
('йога', 'SPORTS', 1), ('гонки', 'SPORTS', 1), ('матч', 'SPORTS', 1), ('кросс', 'SPORTS', 1), ('рафт', 'SPORTS', 1),

-- NORMAL (6-8)
('футбол', 'SPORTS', 2), ('хоккей', 'SPORTS', 2), ('теннис', 'SPORTS', 2), ('прыжки', 'SPORTS', 2), ('борьба', 'SPORTS', 2),
('стрельба', 'SPORTS', 2), ('плавание', 'SPORTS', 2), ('скелетон', 'SPORTS', 2), ('аэробика', 'SPORTS', 2), ('серфинг', 'SPORTS', 2),
('дайвинг', 'SPORTS', 2), ('паркур', 'SPORTS', 2), ('фитнес', 'SPORTS', 2), ('биатлон', 'SPORTS', 2),

-- HARD (9-15)
('бадминтон', 'SPORTS', 3), ('сноубординг', 'SPORTS', 3), ('скейтбординг', 'SPORTS', 3), ('альпинизм', 'SPORTS', 3), ('велоспорт', 'SPORTS', 3),
('фехтование', 'SPORTS', 3), ('гимнастика', 'SPORTS', 3), ('баскетбол', 'SPORTS', 3), ('конькобежец', 'SPORTS', 3), ('легкоатлет', 'SPORTS', 3),

-- TECHNOLOGY
-- EASY (3-5)
('код', 'TECHNOLOGY', 1), ('сайт', 'TECHNOLOGY', 1), ('чат', 'TECHNOLOGY', 1), ('робот', 'TECHNOLOGY', 1), ('файл', 'TECHNOLOGY', 1),
('диск', 'TECHNOLOGY', 1), ('чип', 'TECHNOLOGY', 1), ('сеть', 'TECHNOLOGY', 1), ('бот', 'TECHNOLOGY', 1), ('пакет', 'TECHNOLOGY', 1),
('линк', 'TECHNOLOGY', 1), ('логин', 'TECHNOLOGY', 1),

-- NORMAL (6-8)
('пиксель', 'TECHNOLOGY', 2), ('сервер', 'TECHNOLOGY', 2), ('данные', 'TECHNOLOGY', 2), ('алгоритм', 'TECHNOLOGY', 2), ('функция', 'TECHNOLOGY', 2),
('модуль', 'TECHNOLOGY', 2), ('скрипт', 'TECHNOLOGY', 2), ('плагин', 'TECHNOLOGY', 2), ('система', 'TECHNOLOGY', 2), ('браузер', 'TECHNOLOGY', 2),

-- HARD (9-15)
('процессор', 'TECHNOLOGY', 3), ('интерфейс', 'TECHNOLOGY', 3), ('виртуализация', 'TECHNOLOGY', 3), ('контейнеризация', 'TECHNOLOGY', 3), ('микросервисы', 'TECHNOLOGY', 3),
('распределенный', 'TECHNOLOGY', 3), ('многопоточный', 'TECHNOLOGY', 3), ('автоматизация', 'TECHNOLOGY', 3), ('конфигурация', 'TECHNOLOGY', 3), ('деплоймент', 'TECHNOLOGY', 3),

-- NATURE
-- EASY (3-5)
('лес', 'NATURE', 1), ('поле', 'NATURE', 1), ('гора', 'NATURE', 1), ('река', 'NATURE', 1), ('озеро', 'NATURE', 1),
('море', 'NATURE', 1), ('дождь', 'NATURE', 1), ('снег', 'NATURE', 1), ('ветер', 'NATURE', 1), ('град', 'NATURE', 1),

-- NORMAL (6-8)
('вулкан', 'NATURE', 2), ('остров', 'NATURE', 2), ('пустыня', 'NATURE', 2), ('тундра', 'NATURE', 2), ('саванна', 'NATURE', 2),
('джунгли', 'NATURE', 2), ('водопад', 'NATURE', 2), ('торнадо', 'NATURE', 2), ('цунами', 'NATURE', 2), ('лавина', 'NATURE', 2),
('метеорит', 'NATURE', 2),

-- HARD (9-15)
('атмосфера', 'NATURE', 3), ('стратосфера', 'NATURE', 3), ('фотосинтез', 'NATURE', 3), ('экосистема', 'NATURE', 3), ('биоразнообразие', 'NATURE', 3),
('климатология', 'NATURE', 3), ('геоморфология', 'NATURE', 3), ('гидрология', 'NATURE', 3), ('сейсмология', 'NATURE', 3), ('землетрясение', 'NATURE', 3);