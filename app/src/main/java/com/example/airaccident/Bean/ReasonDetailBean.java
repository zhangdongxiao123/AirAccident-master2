package com.example.airaccident.Bean;

public class ReasonDetailBean {

    /**
     * msg : null
     * status : 0
     * data : {"reaid":"01","reaname":"航空器动力装置故障","reahow":"燃油系统故障、发动机故障"}
     */

    private Object msg;
    private int status;
    private DataBean data;

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * reaid : 01
         * reaname : 航空器动力装置故障
         * reahow : 燃油系统故障、发动机故障
         */

        private String reaid;
        private String reaname;
        private String reahow;

        public String getReaid() {
            return reaid;
        }

        public void setReaid(String reaid) {
            this.reaid = reaid;
        }

        public String getReaname() {
            return reaname;
        }

        public void setReaname(String reaname) {
            this.reaname = reaname;
        }

        public String getReahow() {
            return reahow;
        }

        public void setReahow(String reahow) {
            this.reahow = reahow;
        }
    }
}
