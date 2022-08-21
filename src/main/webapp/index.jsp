<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Поиск пользователя</title>
    </head>
    <body>
        <form name="searchForm" method="post" action="result">
            <h3>
                <label for="search-input">Наличие пользователя Windows</label>
            </h3>
            <input type="text" id="search-input" name="usernameForSearch" required minlength="1" maxlength="20" size="15">
            <input type="submit" value="Проверка"/>
        </form>
    </body>
</html>
