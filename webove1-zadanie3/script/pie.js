$(document).ready(function () {

    $.ajax({
        type: "GET",
        url: "z03.xml",
        dataType: "xml",

        success: function (response) {
            const xmlArray = [];
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

            const allLabels = ["A", "B", "C", "D", "E", "FX", "FN"];

            const allValues = [
                [xmlArray[0][0], xmlArray[0][1], xmlArray[0][2], xmlArray[0][3], xmlArray[0][4], xmlArray[0][5], xmlArray[0][6]],
                [xmlArray[1][0], xmlArray[1][1], xmlArray[1][2], xmlArray[1][3], xmlArray[1][4], xmlArray[1][5], xmlArray[1][6]],
                [xmlArray[2][0], xmlArray[2][1], xmlArray[2][2], xmlArray[2][3], xmlArray[2][4], xmlArray[2][5], xmlArray[2][6]],
                [xmlArray[3][0], xmlArray[3][1], xmlArray[3][2], xmlArray[3][3], xmlArray[3][4], xmlArray[3][5], xmlArray[3][6]],
                [xmlArray[4][0], xmlArray[4][1], xmlArray[4][2], xmlArray[4][3], xmlArray[4][4], xmlArray[4][5], xmlArray[4][6]],
                [xmlArray[5][0], xmlArray[5][1], xmlArray[5][2], xmlArray[5][3], xmlArray[5][4], xmlArray[5][5], xmlArray[5][6]]
            ];
            const pieColors = [
                ['rgb(31 119 180)', 'rgb(255 127 14)', 'rgb(44 160 44)', 'rgb(214 39 40)', 'rgb(148 103 189)', 'rgb(140 86 75)', 'rgb(227 119 194)'],
                ['rgb(31 119 180)', 'rgb(255 127 14)', 'rgb(44 160 44)', 'rgb(214 39 40)', 'rgb(148 103 189)', 'rgb(140 86 75)', 'rgb(227 119 194)'],
                ['rgb(31 119 180)', 'rgb(255 127 14)', 'rgb(44 160 44)', 'rgb(214 39 40)', 'rgb(148 103 189)', 'rgb(140 86 75)', 'rgb(227 119 194)'],
                ['rgb(31 119 180)', 'rgb(255 127 14)', 'rgb(44 160 44)', 'rgb(214 39 40)', 'rgb(148 103 189)', 'rgb(140 86 75)', 'rgb(227 119 194)'],
                ['rgb(31 119 180)', 'rgb(255 127 14)', 'rgb(44 160 44)', 'rgb(214 39 40)', 'rgb(148 103 189)', 'rgb(140 86 75)', 'rgb(227 119 194)'],
                ['rgb(31 119 180)', 'rgb(255 127 14)', 'rgb(44 160 44)', 'rgb(214 39 40)', 'rgb(148 103 189)', 'rgb(140 86 75)', 'rgb(227 119 194)']
            ];

            let pieData = [{
                values: allValues[0],
                labels: allLabels,
                type: 'pie',
                name: xmlArray[0][7],
                marker: {
                    colors: pieColors[0]
                },
                domain: {
                    row: 0,
                    column: 0
                },
                hoverinfo: 'percent+name',
                textinfo: 'label+value'
            }, {
                values: allValues[1],
                labels: allLabels,
                type: 'pie',
                name: xmlArray[1][7],
                marker: {
                    colors: pieColors[1]
                },
                domain: {
                    row: 0,
                    column: 1
                },
                hoverinfo: 'percent+name',
                textinfo: 'label+value'
            }, {
                values: allValues[2],
                labels: allLabels,
                type: 'pie',
                name: xmlArray[2][7],
                marker: {
                    colors: pieColors[2]
                },
                domain: {
                    row: 0,
                    column: 2
                },
                hoverinfo: 'percent+name',
                textinfo: 'label+value'
            }, {
                values: allValues[3],
                labels: allLabels,
                type: 'pie',
                name: xmlArray[3][7],
                marker: {
                    colors: pieColors[3]
                },
                domain: {
                    row: 1,
                    column: 0
                },
                hoverinfo: 'percent+name',
                textinfo: 'label+value'
            }, {
                values: allValues[4],
                labels: allLabels,
                type: 'pie',
                name: xmlArray[4][7],
                marker: {
                    colors: pieColors[4]
                },
                domain: {
                    row: 1,
                    column: 1
                },
                hoverinfo: 'percent+name',
                textinfo: 'label+value'
            }, {
                values: allValues[5],
                labels: allLabels,
                type: 'pie',
                name: xmlArray[5][7],
                marker: {
                    colors: pieColors[5]
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

            window.onresize = function pieChartResponsive(){
                if (window.innerWidth < 920){
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
                }
                else{
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
            }

            // window.onresize(pieChartResponsive(pieData, pieLayout));

            Plotly.newPlot('myPieChart', pieData, pieLayout);
        }
    });
})