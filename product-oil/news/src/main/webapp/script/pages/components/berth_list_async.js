/*所需参数：shipPortCode*/

const url = appServer + "/pages/components/berth_list.json";

function renderBerthList(shipPortCode) {

    $.get(url, {
        'shipPortCode': shipPortCode
    }, function(result) {

        if (result.status == 9999) {
            console.log('Data response error');
            return;
        }

        $('#berthListAsync').empty();
        const berthList = result.datas;

        for (var i = 0; i < berthList.length; i++) {
            var berth = berthList[i];

            var active = i / 2 == 0 ? "" : "'class = active'";
            var tdList =
                '<tr>' +
                    '<td ' + active + '>' + (i + 1) + '</td>' +
                    '<td ' + active + '>' + berth.enName + '</td>' +
                    '<td ' + active + '>' + berth.berthTonnage + '</td>' +
                    '<td ' + active + '>' + berth.berthDraft + '</td>' +
                    '<td ' + active + '>' + berth.vesselType + '</td>' +
                    '<td ' + active + '>' + berth.remark + '</td>' +
                '</tr>';

            $('#berthListAsync').append(tdList);
        }
    })
}