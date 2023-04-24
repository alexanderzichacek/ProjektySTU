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

            if (window.innerWidth < 920) {
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

            let barData = [yearOne, yearTwo, yearThree, yearFour, yearFive, yearSix, yearSeven];
            let barLayout = {barmode: "group"};

            Plotly.newPlot("myBarChart", barData, barLayout);

            const allLabels = ["A", "B", "C", "D", "E", "FX", "FN"];
            let pieData = [{
                values: [xmlArray[0][0], xmlArray[0][1], xmlArray[0][2], xmlArray[0][3], xmlArray[0][4], xmlArray[0][5], xmlArray[0][6]],
                labels: allLabels,
                type: 'pie',
                name: xmlArray[0][7],
                marker: {
                    colors: ['rgb(31 119 180)', 'rgb(255 127 14)', 'rgb(44 160 44)', 'rgb(214 39 40)', 'rgb(148 103 189)', 'rgb(140 86 75)', 'rgb(227 119 194)']
                },
                domain: {
                    row: 0,
                    column: 0
                },
                hoverinfo: 'percent+name',
                textinfo: 'label+value'
            }, {
                values: [xmlArray[1][0], xmlArray[1][1], xmlArray[1][2], xmlArray[1][3], xmlArray[1][4], xmlArray[1][5], xmlArray[1][6]],
                labels: allLabels,
                type: 'pie',
                name: xmlArray[1][7],
                marker: {
                    colors: ['rgb(31 119 180)', 'rgb(255 127 14)', 'rgb(44 160 44)', 'rgb(214 39 40)', 'rgb(148 103 189)', 'rgb(140 86 75)', 'rgb(227 119 194)']
                },
                domain: {
                    row: 0,
                    column: 1
                },
                hoverinfo: 'percent+name',
                textinfo: 'label+value'
            }, {
                values: [xmlArray[2][0], xmlArray[2][1], xmlArray[2][2], xmlArray[2][3], xmlArray[2][4], xmlArray[2][5], xmlArray[2][6]],
                labels: allLabels,
                type: 'pie',
                name: xmlArray[2][7],
                marker: {
                    colors: ['rgb(31 119 180)', 'rgb(255 127 14)', 'rgb(44 160 44)', 'rgb(214 39 40)', 'rgb(148 103 189)', 'rgb(140 86 75)', 'rgb(227 119 194)']
                },
                domain: {
                    row: 0,
                    column: 2
                },
                hoverinfo: 'percent+name',
                textinfo: 'label+value'
            }, {
                values: [xmlArray[3][0], xmlArray[3][1], xmlArray[3][2], xmlArray[3][3], xmlArray[3][4], xmlArray[3][5], xmlArray[3][6]],
                labels: allLabels,
                type: 'pie',
                name: xmlArray[3][7],
                marker: {
                    colors: ['rgb(31 119 180)', 'rgb(255 127 14)', 'rgb(44 160 44)', 'rgb(214 39 40)', 'rgb(148 103 189)', 'rgb(140 86 75)', 'rgb(227 119 194)']
                },
                domain: {
                    row: 1,
                    column: 0
                },
                hoverinfo: 'percent+name',
                textinfo: 'label+value'
            }, {
                values: [xmlArray[4][0], xmlArray[4][1], xmlArray[4][2], xmlArray[4][3], xmlArray[4][4], xmlArray[4][5], xmlArray[4][6]],
                labels: allLabels,
                type: 'pie',
                name: xmlArray[4][7],
                marker: {
                    colors: ['rgb(31 119 180)', 'rgb(255 127 14)', 'rgb(44 160 44)', 'rgb(214 39 40)', 'rgb(148 103 189)', 'rgb(140 86 75)', 'rgb(227 119 194)']
                },
                domain: {
                    row: 1,
                    column: 1
                },
                hoverinfo: 'percent+name',
                textinfo: 'label+value'
            }, {
                values: [xmlArray[5][0], xmlArray[5][1], xmlArray[5][2], xmlArray[5][3], xmlArray[5][4], xmlArray[5][5], xmlArray[5][6]],
                labels: allLabels,
                type: 'pie',
                name: xmlArray[5][7],
                marker: {
                    colors: ['rgb(31 119 180)', 'rgb(255 127 14)', 'rgb(44 160 44)', 'rgb(214 39 40)', 'rgb(148 103 189)', 'rgb(140 86 75)', 'rgb(227 119 194)']
                },
                domain: {
                    row: 1,
                    column: 2
                },
                hoverinfo: 'percent+name',
                textinfo: 'label+value'
            }];
            let pieLayout;
            if (window.innerWidth < 920) {
                pieData[0].domain.row = 0;
                pieData[0].domain.column = 0;
                pieData[1].domain.row = 1;
                pieData[1].domain.column = 0;
                pieData[2].domain.row = 2;
                pieData[2].domain.column = 0;
                pieData[3].domain.row = 3;
                pieData[3].domain.column = 0;
                pieData[4].domain.row = 4;
                pieData[4].domain.column = 0;
                pieData[5].domain.row = 5;
                pieData[5].domain.column = 0;

                pieLayout = {
                    height: 1500,
                    width: 1700,
                    grid: {rows: 6, columns: 1}
                };
            } else {
                pieData[0].domain.row = 0;
                pieData[0].domain.column = 0;
                pieData[1].domain.row = 0;
                pieData[1].domain.column = 1;
                pieData[2].domain.row = 0;
                pieData[2].domain.column = 2;
                pieData[3].domain.row = 1;
                pieData[3].domain.column = 0;
                pieData[4].domain.row = 1;
                pieData[4].domain.column = 1;
                pieData[5].domain.row = 1;
                pieData[5].domain.column = 2;

                pieLayout = {
                    height: 800,
                    width: 1000,
                    grid: {rows: 2, columns: 3}
                };
            }

            Plotly.newPlot('myPieChart', pieData, pieLayout);

            const gradeA = {
                x: year,
                y: [xmlArray[0][0], xmlArray[1][0], xmlArray[2][0], xmlArray[3][0], xmlArray[4][0], xmlArray[5][0]],
                mode: 'lines+markers',
                name: 'A',
                line: {shape: 'spline'},
            };
            const gradeB = {
                x: year,
                y: [xmlArray[0][1], xmlArray[1][1], xmlArray[2][1], xmlArray[3][1], xmlArray[4][1], xmlArray[5][1]],
                mode: 'lines+markers',
                name: 'B',
                line: {shape: 'spline'},
            };
            const gradeC = {
                x: year,
                y: [xmlArray[0][2], xmlArray[1][2], xmlArray[2][2], xmlArray[3][2], xmlArray[4][2], xmlArray[5][2]],
                mode: 'lines+markers',
                name: 'C',
                line: {shape: 'spline'},
            };
            const gradeD = {
                x: year,
                y: [xmlArray[0][3], xmlArray[1][3], xmlArray[2][3], xmlArray[3][3], xmlArray[4][3], xmlArray[5][3]],
                mode: 'lines+markers',
                name: 'D',
                line: {shape: 'spline'},
            };
            const gradeE = {
                x: year,
                y: [xmlArray[0][4], xmlArray[1][4], xmlArray[2][4], xmlArray[3][4], xmlArray[4][4], xmlArray[5][4]],
                mode: 'lines+markers',
                name: 'E',
                line: {shape: 'spline'},
            };
            const gradeFx = {
                x: year,
                y: [xmlArray[0][5], xmlArray[1][5], xmlArray[2][5], xmlArray[3][5], xmlArray[4][5], xmlArray[5][5]],
                mode: 'lines+markers',
                name: 'FX',
                line: {shape: 'spline'},
            };
            const gradeFn = {
                x: year,
                y: [xmlArray[0][6], xmlArray[1][6], xmlArray[2][6], xmlArray[3][6], xmlArray[4][6], xmlArray[5][6]],
                mode: 'lines+markers',
                name: 'FN',
                line: {shape: 'spline'},
            };

            const lineData = [gradeA, gradeB, gradeC, gradeD, gradeE, gradeFx, gradeFn];
            const lineLayout = {barmode: "group"};
            const config = {responsive: true};
            Plotly.newPlot('myLineChart', lineData, lineLayout, config);

            window.onresize = function barChartResponsive() {
                if (window.innerWidth < 920) {
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

                    pieData[0].domain.row = 0;
                    pieData[0].domain.column = 0;
                    pieData[1].domain.row = 1;
                    pieData[1].domain.column = 0;
                    pieData[2].domain.row = 2;
                    pieData[2].domain.column = 0;
                    pieData[3].domain.row = 3;
                    pieData[3].domain.column = 0;
                    pieData[4].domain.row = 4;
                    pieData[4].domain.column = 0;
                    pieData[5].domain.row = 5;
                    pieData[5].domain.column = 0;

                    pieLayout = {
                        height: 1500,
                        width: 1700,
                        grid: {rows: 6, columns: 1}
                    };
                } else {
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

                    pieData[0].domain.row = 0;
                    pieData[0].domain.column = 0;
                    pieData[1].domain.row = 0;
                    pieData[1].domain.column = 1;
                    pieData[2].domain.row = 0;
                    pieData[2].domain.column = 2;
                    pieData[3].domain.row = 1;
                    pieData[3].domain.column = 0;
                    pieData[4].domain.row = 1;
                    pieData[4].domain.column = 1;
                    pieData[5].domain.row = 1;
                    pieData[5].domain.column = 2;

                    pieLayout = {
                        height: 800,
                        width: 1000,
                        grid: {rows: 2, columns: 3}
                    };
                }

                barData = [yearOne, yearTwo, yearThree, yearFour, yearFive, yearSix, yearSeven];
                barLayout = {barmode: "group"};

                Plotly.newPlot("myBarChart", barData, barLayout);
                Plotly.newPlot('myPieChart', pieData, pieLayout);
            }
        }
    });
})