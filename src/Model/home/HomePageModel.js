import * as echarts from 'echarts';

class homePageModel {

    static card4ChartOptions = {
        title: {
            text: '识别类别分布',
            left: 'center',
            top: '3%',
            textStyle: {
                color: '#fff',
                fontSize: 16
            }
        },
        tooltip: { trigger: 'item' },
        series: [{
            type: 'pie',
            radius: '70%',
            data: [
                { value: 735, name: 'personal', itemStyle: { color: '#5470C6' } },
                { value: 580, name: 'car', itemStyle: { color: '#91CC75' } },
                { value: 484, name: 'work', itemStyle: { color: '#FAC858' } }
            ],
            label: { color: '#fff', fontSize: 14 }
        }]
    }
    static card5ChartOptions = {
        title: {
            text: '周访问趋势',
            left: 'center',
            top: '3%',
            textStyle: {
                color: '#000',
                fontSize: 16
            }
        },
        grid: {  // 新增布局配置
            top: '20%',
            right: '5%',
            bottom: '15%',
            left: '10%'
        },
        tooltip: { trigger: 'axis' },
        xAxis: {
            type: 'category',
            data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
        },
        yAxis: { type: 'value' },
        series: [{
            data: [820, 932, 901, 934, 1290, 1330, 1320],
            type: 'line',
            smooth: true,
            areaStyle: { color: 'rgba(75, 192, 192, 0.3)' },
            lineStyle: { color: '#4BC0C0', width: 2 }
        }]
    }
    static card6RadarOption = {
        title: {
            text: '风险评估',
            left: 'center',
            top: '2%',
            textStyle: {
                color: '#000',
                fontSize: 16
            }
        },
        grid: {  // 新增布局配置
            top: '20%',
            right: '5%',
            bottom: '15%',
            left: '10%'
        },
        radar: {
            indicator: [
                {
                    name: '非法入侵',
                    max: 100,
                    textStyle: {
                        color: '#FF6B6B'
                    }
                },
                {
                    name: '设备离线',
                    max: 100,
                    textStyle: {  // 新增
                        color: '#000'
                    }
                },
                {
                    name: '网络异常',
                    max: 100,
                    textStyle: {  // 新增
                        color: '#FAC858'
                    }
                },
                {
                    name: '数据异常',
                    max: 100,
                    textStyle: {  // 新增
                        color: '#91CC75'
                    }
                }
            ],
            axisLabel: { // 新增轴标签样式
                color: '#333' // 设置指标文字颜色
            }
        },

        series: [{
            type: 'radar',
            data: [{
                value: [65, 70, 80, 55]
            }],

        }]
    }

}

export default homePageModel;