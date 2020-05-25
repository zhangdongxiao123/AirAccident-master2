package com.example.airaccident.Other.Weather;

import java.util.List;

public class weaBean {

    /**
     * error : 0
     * status : success
     * date : 2020-05-24
     * results : [{"currentCity":"天津","pm25":"47","index":[{"des":"天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。","tipt":"穿衣指数","title":"穿衣","zs":"热"},{"des":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。","tipt":"洗车指数","title":"洗车","zs":"较适宜"},{"des":"各项气象条件适宜，无明显降温过程，发生感冒机率较低。","tipt":"感冒指数","title":"感冒","zs":"少发"},{"des":"天气较好，赶快投身大自然参与户外运动，尽情感受运动的快乐吧。","tipt":"运动指数","title":"运动","zs":"适宜"},{"des":"紫外线辐射极强，建议涂擦SPF20以上、PA++的防晒护肤品，尽量避免暴露于日光下。","tipt":"紫外线强度指数","title":"紫外线强度","zs":"很强"}],"weather_data":[{"date":"周日 05月24日 (实时：28℃)","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/duoyun.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/qing.png","weather":"多云转晴","wind":"北风3-4级","temperature":"28 ~ 17℃"},{"date":"周一","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/zhenyu.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/zhenyu.png","weather":"阵雨","wind":"东北风3-4级","temperature":"27 ~ 17℃"},{"date":"周二","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/duoyun.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/duoyun.png","weather":"多云","wind":"西北风微风","temperature":"28 ~ 18℃"},{"date":"周三","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/qing.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/qing.png","weather":"晴","wind":"西南风3-4级","temperature":"31 ~ 21℃"}]}]
     */

    private int error;
    private String status;
    private String date;
    private List<ResultsBean> results;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * currentCity : 天津
         * pm25 : 47
         * index : [{"des":"天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。","tipt":"穿衣指数","title":"穿衣","zs":"热"},{"des":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。","tipt":"洗车指数","title":"洗车","zs":"较适宜"},{"des":"各项气象条件适宜，无明显降温过程，发生感冒机率较低。","tipt":"感冒指数","title":"感冒","zs":"少发"},{"des":"天气较好，赶快投身大自然参与户外运动，尽情感受运动的快乐吧。","tipt":"运动指数","title":"运动","zs":"适宜"},{"des":"紫外线辐射极强，建议涂擦SPF20以上、PA++的防晒护肤品，尽量避免暴露于日光下。","tipt":"紫外线强度指数","title":"紫外线强度","zs":"很强"}]
         * weather_data : [{"date":"周日 05月24日 (实时：28℃)","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/duoyun.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/qing.png","weather":"多云转晴","wind":"北风3-4级","temperature":"28 ~ 17℃"},{"date":"周一","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/zhenyu.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/zhenyu.png","weather":"阵雨","wind":"东北风3-4级","temperature":"27 ~ 17℃"},{"date":"周二","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/duoyun.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/duoyun.png","weather":"多云","wind":"西北风微风","temperature":"28 ~ 18℃"},{"date":"周三","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/qing.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/qing.png","weather":"晴","wind":"西南风3-4级","temperature":"31 ~ 21℃"}]
         */

        private String currentCity;
        private String pm25;
        private List<IndexBean> index;
        private List<WeatherDataBean> weather_data;

        public String getCurrentCity() {
            return currentCity;
        }

        public void setCurrentCity(String currentCity) {
            this.currentCity = currentCity;
        }

        public String getPm25() {
            return pm25;
        }

        public void setPm25(String pm25) {
            this.pm25 = pm25;
        }

        public List<IndexBean> getIndex() {
            return index;
        }

        public void setIndex(List<IndexBean> index) {
            this.index = index;
        }

        public List<WeatherDataBean> getWeather_data() {
            return weather_data;
        }

        public void setWeather_data(List<WeatherDataBean> weather_data) {
            this.weather_data = weather_data;
        }

        public static class IndexBean {
            /**
             * des : 天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。
             * tipt : 穿衣指数
             * title : 穿衣
             * zs : 热
             */

            private String des;
            private String tipt;
            private String title;
            private String zs;

            public String getDes() {
                return des;
            }

            public void setDes(String des) {
                this.des = des;
            }

            public String getTipt() {
                return tipt;
            }

            public void setTipt(String tipt) {
                this.tipt = tipt;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getZs() {
                return zs;
            }

            public void setZs(String zs) {
                this.zs = zs;
            }
        }

        public static class WeatherDataBean {
            /**
             * date : 周日 05月24日 (实时：28℃)
             * dayPictureUrl : http://api.map.baidu.com/images/weather/day/duoyun.png
             * nightPictureUrl : http://api.map.baidu.com/images/weather/night/qing.png
             * weather : 多云转晴
             * wind : 北风3-4级
             * temperature : 28 ~ 17℃
             */

            private String date;
            private String dayPictureUrl;
            private String nightPictureUrl;
            private String weather;
            private String wind;
            private String temperature;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getDayPictureUrl() {
                return dayPictureUrl;
            }

            public void setDayPictureUrl(String dayPictureUrl) {
                this.dayPictureUrl = dayPictureUrl;
            }

            public String getNightPictureUrl() {
                return nightPictureUrl;
            }

            public void setNightPictureUrl(String nightPictureUrl) {
                this.nightPictureUrl = nightPictureUrl;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public String getWind() {
                return wind;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }
        }
    }
}
