<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Overwatch Dashboard</title>
        <link rel="stylesheet" href="index.css">
    </head>
    <body>
        <div class="header">
            <h1>Overwatch</h1>
            <h1 class="orange">Dashboard</h1>
        </div>
        <ul>
        <#list battleTags as battleTag>
            <li>
                <div class="card">
                    <div class="mask"></div>
                    <div class="hero">
                        <img src="https://blzgdapipro-a.akamaihd.net/hero/${data[battleTag].mainComp!"genji"}/hero-select-portrait.png" alt=""/>
                    </div>
                    <div class="bottom-panel">
                        <img src="${data[battleTag].avatar!""}" alt="" class="player-icon">
                        <div class="details">
                            <span class="battleTag">${battleTag}</span>
                            <span>SR: ${data[battleTag].seasonRating!"NA"}</span>
                        </div>
                    </div>
                </div>
            </li>
        </#list>
        </ul>
    </body>
</html>