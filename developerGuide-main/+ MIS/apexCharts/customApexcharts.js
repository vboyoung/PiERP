/*  */

(function(){

    window.addEventListener('DOMContentLoaded', function(){

        Apex.grid = {
            padding: {
            right: 0,
            left: 0
            }
        }

        Apex.dataLabels = {
          enabled: false
        }


    var colorPalette = ['#00D8B6','#008FFB',  '#FEB019', '#FF4560', '#775DD0'];

    <!-- chart bar -->
    var optionsBar = {
      chart: {
        id: 'chartbar',
        type: 'bar',
        height: 350,

        stacked: true,
      },
      plotOptions: {
        bar: {
          columnWidth: '45%',
        }
      },
      colors: colorPalette,
      series: [{
        name: "Clothing",
        data: [],
      }, {
        name: "Food Products",
        data: [],
      }],
      labels: [10,11,12,13,14,15,16,17,18,19,20,21,22,23],
      xaxis: {
        labels: {
          show: false
        },
        axisBorder: {
          show: false
        },
        axisTicks: {
          show: false
        },
      },
      yaxis: {
        axisBorder: {
          show: false
        },
        axisTicks: {
          show: false
        },
        labels: {
          style: {
            colors: '#78909c'
          }
        }
      },
      title: {
        text: '차트 예제',
        align: 'left',
        style: {
          fontSize: '18px'
        }
      }

    }

    var chartBar = new ApexCharts(document.querySelector('#bar'), optionsBar);
    chartBar.render();




    <!-- line chart -->
    var options = {
      chart: {
          id: 'chartline',
          height: 350,
          type: 'bar',
      },
      dataLabels: {
          enabled: false
      },
      series: {
        name : '',
        data : []
      },
      title: {
          text: '차트 예제',
          align: 'left',
        style: {
          fontSize: '18px'
        }
      },
      noData: {
        text: '준비중입니다.'
      }
    }

    var chart = new ApexCharts(
      document.querySelector("#chart"),
      options
    );

    chart.render();



    <!-- twoline chart -->
     var optionTwoLine = {
      chart: {
        id : 'chartTwoLine',
        type: 'bar',
        height: 350,
        width: '100%',
        stacked: true,
        foreColor: '#999',
      },
      plotOptions: {
        bar: {
          dataLabels: {
            enabled: false
          },
          columnWidth: '75%',
          endingShape: 'rounded'
        }
      },
      colors: ["#00C5A4", '#F3F2FC'],
      series: [{
        name: "Sessions",
        data: [],
      }, {
        name: "Views",
        data: [],
      }],
      labels: [15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 1, 2, 3, 4],
      xaxis: {
        axisBorder: {
          show: false
        },
        axisTicks: {
          show: false
        },
        crosshairs: {
          show: false
        },
        labels: {
          show: false,
          style: {
            fontSize: '14px'
          }
        },
      },
      grid: {
        xaxis: {
          lines: {
            show: false
          },
        },
        yaxis: {
          lines: {
            show: false
          },
        }
      },
      yaxis: {
        axisBorder: {
          show: false
        },
        labels: {
          show: false
        },
      },
      legend: {
        floating: true,
        position: 'top',
        horizontalAlign: 'right',
        offsetY: -36
      },
      title: {
        text: 'Web Statistics',
        align: 'left',
      },
      subtitle: {
        text: 'Sessions and Views'
      },
      tooltip: {
        shared: true,
        intersect: false
      }

    }

    var chartTwoLine = new ApexCharts(document.querySelector('#twoline'), optionTwoLine);
    chartTwoLine.render();



    <!-- donut chart -->
    var optionDonut = {
        chart: {
            id: 'chartdonut',
            type: 'donut',
            width: '100%',
            height: 400
        },
        dataLabels: {
        enabled: false,
        },
        plotOptions: {
        pie: {
            customScale: 0.8,
            donut: {
            size: '75%',
            },
            offsetY: 20,
        },
        stroke: {
            colors: undefined
        }
        },
        colors: colorPalette,
        title: {
          text: '차트 예제',
          style: {
              fontSize: '18px'
          }
        },
        series: [],

        labels: ['Clothing', 'Food Products', 'Electronics', 'Kitchen Utility', 'Gardening'],
        legend: {
        position: 'left',
        offsetY: 80
        }
    }

    var donut = new ApexCharts(
      document.querySelector("#donut"),
      optionDonut
    )

    donut.render();



    <!-- radialBar chart -->
    var optionRadial = {
      chart: {
        id : 'radialdonut',
        height: 350,
        type: 'radialBar',
      },
      colors: ['#775DD0', '#00C8E1', '#FFB900'],
      labels: ['q4'],
      series: [],
      labels: ['June', 'May', 'April'],
      theme: {
        monochrome: {
          enabled: false
        }
      },
      plotOptions: {
        radialBar: {
          offsetY: -30
        }
      },
      legend: {
        show: true,
        position: 'left',
        containerMargin: {
          right: 0
        }
      },
      title: {
        text: 'Growth'
      }
    }

    var radial = new ApexCharts(document.querySelector('#radial'), optionRadial);
    radial.render();



        <!-- Adword chart -->

        var optionsAdword = {
        chart: {
          id: 'chartAdword',
          height: 421,
          type: 'area',
          background: '#fff',
          stacked: true,
          offsetY: 39,
          zoom: {
            enabled: false
          }
        },
        plotOptions: {
          line: {
            dataLabels: {
              enabled: false
            }
          }
        },
        stroke: {
          curve: 'straight'
        },
        colors: ["#3F51B5", '#2196F3'],
        series: [{
            name: "Adwords Views",
            data: [15, 26, 20, 33, 27, 43, 17, 26, 19]
          },
          {
            name: "Adwords Clicks",
            data: [33, 21, 42, 19, 32, 25, 36, 29, 49]
          }
        ],
        fill: {
          type: 'gradient',
          gradient: {
            inverseColors: false,
            shade: 'light',
            type: "vertical",
            opacityFrom: 0.9,
            opacityTo: 0.6,
            stops: [0, 100, 100, 100]
          }
        },
        title: {
          text: 'Visitor Insights',
          align: 'left',
          offsetY: -5,
          offsetX: 20
        },
        subtitle: {
          text: 'Adwords Statistics',
          offsetY: 30,
          offsetX: 20
        },
        markers: {
          size: 0,
          style: 'hollow',
          strokeWidth: 8,
          strokeColor: "#fff",
          strokeOpacity: 0.25,
        },
        grid: {
          show: false,
          padding: {
            left: 0,
            right: 0
          }
        },
        yaxis: {
          show: false
        },
        labels: ['01/15/2002', '01/16/2002', '01/17/2002', '01/18/2002', '01/19/2002', '01/20/2002', '01/21/2002', '01/22/2002', '01/23/2002'],
        xaxis: {
          type: 'datetime',
          tooltip: {
            enabled: false
          }
        },
        legend: {
          offsetY: -50,
          position: 'top',
          horizontalAlign: 'right'
        }
      }

      var AdwordChart = new ApexCharts(document.querySelector('#adwords'), optionsAdword);
      AdwordChart.render();



      <!-- line-adwords chart -->
      var optionsLine = {
        chart: {
          id : 'lineAdwords',
          height: 328,
          type: 'line',
          zoom: {
            enabled: false
          }
        },
        stroke: {
          curve: 'smooth',
          width: 2
        },
        //colors: ["#3F51B5", '#2196F3'],
        series: [{
            name: "Music",
            data: []
          },
          {
            name: "Photos",
            data: []
          }
        ],
        title: {
          text: 'Media',
          align: 'left',
          offsetY: 25,
          offsetX: 20
        },
        subtitle: {
          text: 'Statistics',
          offsetY: 55,
          offsetX: 20
        },
        markers: {
          size: 6,
          strokeWidth: 0,
          hover: {
            size: 9
          }
        },
        grid: {
          show: true,
          padding: {
            bottom: 0
          }
        },
        labels: ['01/15/2002', '01/16/2002', '01/17/2002', '01/18/2002', '01/19/2002', '01/20/2002'],
        xaxis: {
          tooltip: {
            enabled: false
          }
        },
        legend: {
          position: 'top',
          horizontalAlign: 'right',
          offsetY: -20
        }
      }

      var ChartLineAdword = new ApexCharts(document.querySelector('#line-adwords'), optionsLine);
      ChartLineAdword.render();


    }) //DOMContentLoaded end!

})();






  /* create chart-Data-object*/
  var Mychart = function(id, data, name) {

    this.id = id;
    this.data = data;
    this.name = name;

    Mychart.prototype.exec = function() {
      return ApexCharts.exec(this.id, "updateSeries", this.data);
    }

    Mychart.prototype.execName = function() {
      return ApexCharts.exec(this.id, "updateSeries", [{ name : this.name , data: this.data }]);
    }

    Mychart.prototype.execGroup = function() {
      return ApexCharts.exec(this.id, "updateSeries", [{data : this.data},{data : this.data} ]);
    }

  };



  /* apexChart Function */
  function arrData (arg) {

     var _data = [];
     for(var i=0; i< arg.length; i++) {
        _data.push( Object.assign( {x: arg[i], y : arg[i] }) );
     }
     return _data;
  }




  function optionSet (_data) {

    var colorPalette = ['#00D8B6','#008FFB',  '#FEB019', '#FF4560', '#775DD0'];
    var optionsArea = {
        chart: {
          height: 340,
          type: 'area',
          zoom: {
            enabled: false
          },
        },
        stroke: {
          curve: 'straight'
        },
        colors: colorPalette,
        series: [

          { data: _data },

          ],
        fill: {
          opacity: 1,
        },
        title: {
          text: 'Daily Visits Insights',
          align: 'left',
          style: {
            fontSize: '18px'
          }
        },
        markers: {
          size: 0,
          style: 'hollow',
          hover: {
            opacity: 5,
          }
        },
        tooltip: {
          intersect: true,
          shared: false,
        },
        xaxis: {
          tooltip: {
            enabled: false
          },
          labels: {
            show: false
          },
          axisTicks: {
            show: false
          }
        },
        yaxis: {
          tickAmount: 4,
          max: 12,
          axisBorder: {
            show: false
          },
          axisTicks: {
            show: false
          },
          labels: {
            style: {
              colors: '#78909c'
            }
          }
        },
        legend: {
          show: false
        }
      }

    var chartArea = new ApexCharts(document.querySelector('#area'), optionsArea);
    return chartArea.render();

  }









