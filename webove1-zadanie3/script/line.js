

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

            const skRok = [xmlArray[0][7], xmlArray[1][7], xmlArray[2][7], xmlArray[3][7], xmlArray[4][7], xmlArray[5][7]]
            const trace1 = {
                x: skRok,
                y: [xmlArray[0][0], xmlArray[1][0], xmlArray[2][0], xmlArray[3][0], xmlArray[4][0], xmlArray[5][0]],
                mode: 'lines+markers',
                name: 'A',
                line: {shape: 'spline'},
            };
            const trace2 = {
                x: skRok,
                y: [xmlArray[0][1], xmlArray[1][1], xmlArray[2][1], xmlArray[3][1], xmlArray[4][1], xmlArray[5][1]],
                mode: 'lines+markers',
                name: 'B',
                line: {shape: 'spline'},
            };
            const trace3 = {
                x: skRok,
                y: [xmlArray[0][2], xmlArray[1][2], xmlArray[2][2], xmlArray[3][2], xmlArray[4][2], xmlArray[5][2]],
                mode: 'lines+markers',
                name: 'C',
                line: {shape: 'spline'},
            };
            const trace4 = {
                x: skRok,
                y: [xmlArray[0][3], xmlArray[1][3], xmlArray[2][3], xmlArray[3][3], xmlArray[4][3], xmlArray[5][3]],
                mode: 'lines+markers',
                name: 'D',
                line: {shape: 'spline'},
            };
            const trace5 = {
                x: skRok,
                y: [xmlArray[0][4], xmlArray[1][4], xmlArray[2][4], xmlArray[3][4], xmlArray[4][4], xmlArray[5][4]],
                mode: 'lines+markers',
                name: 'E',
                line: {shape: 'spline'},
            };
            const trace6 = {
                x: skRok,
                y: [xmlArray[0][5], xmlArray[1][5], xmlArray[2][5], xmlArray[3][5], xmlArray[4][5], xmlArray[5][5]],
                mode: 'lines+markers',
                name: 'FX',
                line: {shape: 'spline'},
            };
            const trace7 = {
                x: skRok,
                y: [xmlArray[0][6], xmlArray[1][6], xmlArray[2][6], xmlArray[3][6], xmlArray[4][6], xmlArray[5][6]],
                mode: 'lines+markers',
                name: 'FN',
                line: {shape: 'spline'},
            };

            const data = [trace1, trace2, trace3, trace4, trace5, trace6, trace7];
            const layout = {barmode: "group"};
            var config = {responsive: true}
            Plotly.newPlot('myLineChart', data, layout, config);
        }
    });
})