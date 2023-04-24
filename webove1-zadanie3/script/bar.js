const xmlArray = [];

$(document).ready(function () {

    $.ajax({
        type: "GET",
        url: "z03.xml",
        dataType: "xml",

        success: function (response) {

            $(response).find("zaznam").each(function () {
                const A = $(this).find('A').text();
                const B = $(this).find('B').text();
                const C = $(this).find('C').text();
                const D = $(this).find('D').text();
                const E = $(this).find('E').text();
                const FX = $(this).find('FX').text();
                const FN = $(this).find('FN').text();
                const rok = $(this).find('rok').text();

                xmlArray.push([A, B, C, D, E, FX, FN, rok])
            });

            const year = [xmlArray[0][7], xmlArray[1][7], xmlArray[2][7], xmlArray[3][7], xmlArray[4][7], xmlArray[5][7]];
            let barData;
            let barLayout;
            let yearOne = {
                x: year,
                y: [xmlArray[0][0], xmlArray[1][0], xmlArray[2][0], xmlArray[3][0], xmlArray[4][0], xmlArray[5][0]],
                name: "A",
                type: "bar",
                orientation: "v",
                width: "0.12"
            };
            let yearTwo = {
                x: year,
                y: [xmlArray[0][1], xmlArray[1][1], xmlArray[2][1], xmlArray[3][1], xmlArray[4][1], xmlArray[5][1]],
                name: "B",
                type: "bar",
                orientation: "v",
                width: "0.12"
            };
            let yearThree = {
                x: year,
                y: [xmlArray[0][2], xmlArray[1][2], xmlArray[2][2], xmlArray[3][2], xmlArray[4][2], xmlArray[5][2]],
                name: "C",
                type: "bar",
                orientation: "v",
                width: "0.12"
            };
            let yearFour = {
                x: year,
                y: [xmlArray[0][3], xmlArray[1][3], xmlArray[2][3], xmlArray[3][3], xmlArray[4][3], xmlArray[5][3]],
                name: "D",
                type: "bar",
                orientation: "v",
                width: "0.12"
            };
            let yearFive = {
                x: year,
                y: [xmlArray[0][4], xmlArray[1][4], xmlArray[2][4], xmlArray[3][4], xmlArray[4][4], xmlArray[5][4]],
                name: "E",
                type: "bar",
                orientation: "v",
                width: "0.12"
            };
            let yearSix = {
                x: year,
                y: [xmlArray[0][5], xmlArray[1][5], xmlArray[2][5], xmlArray[3][5], xmlArray[4][5], xmlArray[5][5]],
                name: "FX",
                type: "bar",
                orientation: "v",
                width: "0.12"
            };
            let yearSeven = {
                x: year,
                y: [xmlArray[0][6], xmlArray[1][6], xmlArray[2][6], xmlArray[3][6], xmlArray[4][6], xmlArray[5][6]],
                name: "FN",
                type: "bar",
                orientation: "v",
                width: "0.12"
            };

            if (window.innerWidth < 920){
                yearOne.y = year;
                yearOne.x = [xmlArray[0][0], xmlArray[1][0], xmlArray[2][0], xmlArray[3][0], xmlArray[4][0], xmlArray[5][0]];
                yearOne.orientation = "h";
                yearTwo.y = year;
                yearTwo.x = [xmlArray[0][1], xmlArray[1][1], xmlArray[2][1], xmlArray[3][1], xmlArray[4][1], xmlArray[5][1]];
                yearTwo.orientation = "h";
                yearThree.y = year;
                yearThree.x = [xmlArray[0][2], xmlArray[1][2], xmlArray[2][2], xmlArray[3][2], xmlArray[4][2], xmlArray[5][2]];
                yearThree.orientation = "h";
                yearFour.y = year;
                yearFour.x = [xmlArray[0][3], xmlArray[1][3], xmlArray[2][3], xmlArray[3][3], xmlArray[4][3], xmlArray[5][3]];
                yearFour.orientation = "h";
                yearFive.y = year;
                yearFive.x = [xmlArray[0][0], xmlArray[1][4], xmlArray[2][4], xmlArray[3][4], xmlArray[4][4], xmlArray[5][4]];
                yearFive.orientation = "h";
                yearSix.y = year;
                yearSix.x = [xmlArray[0][5], xmlArray[1][5], xmlArray[2][5], xmlArray[3][5], xmlArray[4][5], xmlArray[5][5]];
                yearSix.orientation = "h";
                yearSeven.y = year;
                yearSeven.x = [xmlArray[0][6], xmlArray[1][6], xmlArray[2][6], xmlArray[3][6], xmlArray[4][6], xmlArray[5][6]];
                yearSeven.orientation = "h";
            }

            window.onresize = function barChartResponsive(){
            // function barChartResponsive(){
                if (window.innerWidth < 920){
                    yearOne.y = year;
                    yearOne.x = [xmlArray[0][0], xmlArray[1][0], xmlArray[2][0], xmlArray[3][0], xmlArray[4][0], xmlArray[5][0]];
                    yearOne.orientation = "h";
                    yearTwo.y = year;
                    yearTwo.x = [xmlArray[0][1], xmlArray[1][1], xmlArray[2][1], xmlArray[3][1], xmlArray[4][1], xmlArray[5][1]];
                    yearTwo.orientation = "h";
                    yearThree.y = year;
                    yearThree.x = [xmlArray[0][2], xmlArray[1][2], xmlArray[2][2], xmlArray[3][2], xmlArray[4][2], xmlArray[5][2]];
                    yearThree.orientation = "h";
                    yearFour.y = year;
                    yearFour.x = [xmlArray[0][3], xmlArray[1][3], xmlArray[2][3], xmlArray[3][3], xmlArray[4][3], xmlArray[5][3]];
                    yearFour.orientation = "h";
                    yearFive.y = year;
                    yearFive.x = [xmlArray[0][0], xmlArray[1][4], xmlArray[2][4], xmlArray[3][4], xmlArray[4][4], xmlArray[5][4]];
                    yearFive.orientation = "h";
                    yearSix.y = year;
                    yearSix.x = [xmlArray[0][5], xmlArray[1][5], xmlArray[2][5], xmlArray[3][5], xmlArray[4][5], xmlArray[5][5]];
                    yearSix.orientation = "h";
                    yearSeven.y = year;
                    yearSeven.x = [xmlArray[0][6], xmlArray[1][6], xmlArray[2][6], xmlArray[3][6], xmlArray[4][6], xmlArray[5][6]];
                    yearSeven.orientation = "h";
                }
                else {
                    yearOne.x = year;
                    yearOne.y = [xmlArray[0][0], xmlArray[1][0], xmlArray[2][0], xmlArray[3][0], xmlArray[4][0], xmlArray[5][0]];
                    yearOne.orientation = "v";
                    yearTwo.x = year;
                    yearTwo.y = [xmlArray[0][1], xmlArray[1][1], xmlArray[2][1], xmlArray[3][1], xmlArray[4][1], xmlArray[5][1]];
                    yearTwo.orientation = "v";
                    yearThree.x = year;
                    yearThree.y = [xmlArray[0][2], xmlArray[1][2], xmlArray[2][2], xmlArray[3][2], xmlArray[4][2], xmlArray[5][2]];
                    yearThree.orientation = "v";
                    yearFour.x = year;
                    yearFour.y = [xmlArray[0][3], xmlArray[1][3], xmlArray[2][3], xmlArray[3][3], xmlArray[4][3], xmlArray[5][3]];
                    yearFour.orientation = "v";
                    yearFive.x = year;
                    yearFive.y = [xmlArray[0][4], xmlArray[1][4], xmlArray[2][4], xmlArray[3][4], xmlArray[4][4], xmlArray[5][4]];
                    yearFive.orientation = "v";
                    yearSix.x = year;
                    yearSix.y = [xmlArray[0][5], xmlArray[1][5], xmlArray[2][5], xmlArray[3][5], xmlArray[4][5], xmlArray[5][5]];
                    yearSix.orientation = "v";
                    yearSeven.x = year;
                    yearSeven.y = [xmlArray[0][6], xmlArray[1][6], xmlArray[2][6], xmlArray[3][6], xmlArray[4][6], xmlArray[5][6]];
                    yearSeven.orientation = "v";
                }

                barData = [yearOne, yearTwo, yearThree, yearFour, yearFive, yearSix, yearSeven];
                barLayout = {barmode: "group"};

                Plotly.newPlot("myBarChart", barData, barLayout);
            }

            // window.onresize(barChartResponsive(barData, barLayout, yearOne, yearTwo, yearThree, yearFour, yearFive, yearSix, yearSeven, year));

            barData = [yearOne, yearTwo, yearThree, yearFour, yearFive, yearSix, yearSeven];
            barLayout = {barmode: "group"};

            Plotly.newPlot("myBarChart", barData, barLayout);
        }
    });
})

