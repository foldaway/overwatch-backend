<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Overwatch Dashboard</title>
        <link rel="stylesheet" href="index.css">
    </head>
    <body>
        <div class="header">
            <h1>Overwatch</h1><h1 style="color: orange"> Dashboard</h1>
        </div>

        <ul>
        <#list battleTags as battleTag>
            <li>
                <div class="card">
                    <img src="https://blzgdapipro-a.akamaihd.net/hero/${data[battleTag].mainComp!"genji"}/hero-select-portrait.png" alt="" class="hero">
                    <div class="mask">
                        <img src="${data[battleTag].avatar!""}" alt="" class="avatar">
                        <h1>${battleTag}</h1>
                        <p>Season SR: ${data[battleTag].seasonRating!"NA"}</p>
                    </div>
                </div>
            </li>
        </#list>
        </ul>
    </body>
</html>