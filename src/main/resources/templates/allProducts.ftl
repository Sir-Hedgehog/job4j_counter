<!DOCTYPE html>
<html lang="ru">

    <head>
        <meta charset="UTF-8" />
        <title>Список продуктов</title>
        <style>
            #line_block {
                width:110px;
                height:50px;
                background:#f1f1f1;
                float:left;
                margin: 0 15px 15px 0;
                text-align:center;
                padding: 10px;
            }
        </style>
        <script>
            var conceptName = $('#aioConceptName').find(":selected").text();
        </script>
    </head>
    <body>
        <section class="products">
            <div>
                <label for="select-categories" class="label select-box1"><span class="label-desc">Выберите категорию</span>
                    <select name="categories">
                        <#list categories as category>
                            <option>${category}</option>
                        </#list>
                    </select>
                </label>
            </div>
            <div>
                <#list products as product>
                    <div id="line_block">
                        ${product.name}
                        ${product.calories}
                    </div>
                </#list>
            </div>
            <div>
                <span>
                    <a href="${'/products/new'}">Добавить новый продукт</a>
                </span>
            </div>
        </section>
    </body>
</html>