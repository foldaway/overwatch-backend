<html>
    <head>
        <title>Overwatch Dashboard</title>
    </head>
    <body>
       <#list keys as battleTag>
           <li>
               <h1>${battleTag}</h1>
               <img src="${data[battleTag]["avatar"]}" alt="" class="avatar">
               <p>You've got awesome ${data[battleTag]["sr"]}</p>
           </li>
       </#list>
    </body>
</html>