<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Overwatch Dashboard</title>
        <link rel="stylesheet" href="static/index.css">
    </head>
    <body>
        <ul>
            <#list battleTags as battleTag>
                <li>
                    <h1>${battleTag}</h1>
                    <img src="${data[battleTag].avatar!""}" alt="" class="avatar">
                    <img src="https://blzgdapipro-a.akamaihd.net/hero/${data[battleTag].mainComp!"genji"}/career-portrait.png" alt="" class="hero">
                    <p>Season SR: ${data[battleTag].seasonRating!"NA"}</p>
                </li>
            </#list>
        </ul>
    </body>
</html>