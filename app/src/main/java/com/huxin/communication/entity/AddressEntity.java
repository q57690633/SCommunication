package com.huxin.communication.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangzanxiong on 2018/1/22.
 */

public class AddressEntity implements Parcelable {

    /**
     * id : A340000
     * name : 安徽省
     * list : [{"id":"A340800","name":"安庆市","list":[{"id":"D340803","name":"大观区"},{"id":"H340822","name":"怀宁县"},{"id":"Q340824","name":"潜山县"},{"id":"S340826","name":"宿松县"},{"id":"T340825","name":"太湖县"},{"id":"T340881","name":"桐城市"},{"id":"W340827","name":"望江县"},{"id":"Y340802","name":"迎江区"},{"id":"Y340811","name":"宜秀区"},{"id":"Y340828","name":"岳西县"},{"id":"Z340823","name":"枞阳县"}]},{"id":"B340300","name":"蚌埠市","list":[{"id":"B340303","name":"蚌山区"},{"id":"G340323","name":"固镇县"},{"id":"H340311","name":"淮上区"},{"id":"H340321","name":"怀远县"},{"id":"L340302","name":"龙子湖区"},{"id":"W340322","name":"五河县"},{"id":"Y340304","name":"禹会区"}]},{"id":"B341600","name":"亳州市","list":[{"id":"G341621","name":"涡阳县"},{"id":"L341623","name":"利辛县"},{"id":"M341622","name":"蒙城县"},{"id":"Q341602","name":"谯城区"}]},{"id":"C341100","name":"滁州市","list":[{"id":"D341125","name":"定远县"},{"id":"F341126","name":"凤阳县"},{"id":"L341102","name":"琅琊区"},{"id":"L341122","name":"来安县"},{"id":"M341182","name":"明光市"},{"id":"N341103","name":"南谯区"},{"id":"Q341124","name":"全椒县"},{"id":"T341181","name":"天长市"}]},{"id":"C341700","name":"池州市","list":[{"id":"D341721","name":"东至县"},{"id":"G341702","name":"贵池区"},{"id":"Q341723","name":"青阳县"},{"id":"S341722","name":"石台县"}]},{"id":"F341200","name":"阜阳市","list":[{"id":"F341225","name":"阜南县"},{"id":"J341282","name":"界首市"},{"id":"L341221","name":"临泉县"},{"id":"T341222","name":"太和县"},{"id":"Y341202","name":"颍州区"},{"id":"Y341203","name":"颍东区"},{"id":"Y341204","name":"颍泉区"},{"id":"Y341226","name":"颍上县"}]},{"id":"H340100","name":"合肥市","list":[{"id":"B340111","name":"包河区"},{"id":"C340121","name":"长丰县"},{"id":"C340181","name":"巢湖市"},{"id":"F340122","name":"肥东县"},{"id":"F340123","name":"肥西县"},{"id":"L340103","name":"庐阳区"},{"id":"L340124","name":"庐江县"},{"id":"S340104","name":"蜀山区"},{"id":"Y340102","name":"瑶海区"}]},{"id":"H340400","name":"淮南市","list":[{"id":"B340405","name":"八公山区"},{"id":"D340402","name":"大通区"},{"id":"F340421","name":"凤台县"},{"id":"P340406","name":"潘集区"},{"id":"T340403","name":"田家庵区"},{"id":"X340404","name":"谢家集区"}]},{"id":"H340600","name":"淮北市","list":[{"id":"D340602","name":"杜集区"},{"id":"L340604","name":"烈山区"},{"id":"S340621","name":"濉溪县"},{"id":"X340603","name":"相山区"}]},{"id":"H341000","name":"黄山市","list":[{"id":"H341003","name":"黄山区"},{"id":"H341004","name":"徽州区"},{"id":"Q341024","name":"祁门县"},{"id":"S341021","name":"歙县"},{"id":"T341002","name":"屯溪区"},{"id":"X341022","name":"休宁县"},{"id":"Y341023","name":"黟县"}]},{"id":"L341500","name":"六安市","list":[{"id":"H341522","name":"霍邱县"},{"id":"H341525","name":"霍山县"},{"id":"J341502","name":"金安区"},{"id":"J341524","name":"金寨县"},{"id":"S341521","name":"寿县"},{"id":"S341523","name":"舒城县"},{"id":"Y341503","name":"裕安区"}]},{"id":"M340500","name":"马鞍山市","list":[{"id":"B340506","name":"博望区"},{"id":"D340521","name":"当涂县"},{"id":"H340503","name":"花山区"},{"id":"H340522","name":"含山县"},{"id":"H340523","name":"和县"},{"id":"Y340504","name":"雨山区"}]},{"id":"S341300","name":"宿州市","list":[{"id":"D341321","name":"砀山县"},{"id":"L341323","name":"灵璧县"},{"id":"S341324","name":"泗县"},{"id":"X341322","name":"萧县"},{"id":"Y341302","name":"埇桥区"}]},{"id":"T340700","name":"铜陵市","list":[{"id":"J340711","name":"郊区"},{"id":"S340703","name":"狮子山区"},{"id":"T340702","name":"铜官山区"},{"id":"T340721","name":"铜陵县"}]},{"id":"W340200","name":"芜湖市","list":[{"id":"F340222","name":"繁昌县"},{"id":"J340202","name":"镜湖区"},{"id":"J340207","name":"鸠江区"},{"id":"N340223","name":"南陵县"},{"id":"S340208","name":"三山区"},{"id":"W340221","name":"芜湖县"},{"id":"W340225","name":"无为县"},{"id":"Y340203","name":"弋江区"}]},{"id":"X341800","name":"宣城市","list":[{"id":"G341822","name":"广德县"},{"id":"J341823","name":"泾县"},{"id":"J341824","name":"绩溪县"},{"id":"J341825","name":"旌德县"},{"id":"L341821","name":"郎溪县"},{"id":"N341881","name":"宁国市"},{"id":"X341802","name":"宣州区"}]}]
     */

    private String id;
    private String name;
    private List<ListBeanX> list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ListBeanX> getList() {
        return list;
    }

    public void setList(List<ListBeanX> list) {
        this.list = list;
    }

    public static class ListBeanX implements Parcelable {

        /**
         * id : A340800
         * name : 安庆市
         * list : [{"id":"D340803","name":"大观区"},{"id":"H340822","name":"怀宁县"},{"id":"Q340824","name":"潜山县"},{"id":"S340826","name":"宿松县"},{"id":"T340825","name":"太湖县"},{"id":"T340881","name":"桐城市"},{"id":"W340827","name":"望江县"},{"id":"Y340802","name":"迎江区"},{"id":"Y340811","name":"宜秀区"},{"id":"Y340828","name":"岳西县"},{"id":"Z340823","name":"枞阳县"}]
         */

        private String id;
        private String name;
        private List<ListBean> list;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Parcelable {
            /**
             * id : D340803
             * name : 大观区
             */

            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.id);
                dest.writeString(this.name);
            }

            public ListBean() {
            }

            protected ListBean(Parcel in) {
                this.id = in.readString();
                this.name = in.readString();
            }

            public static final Creator<ListBean> CREATOR = new Creator<ListBean>() {
                @Override
                public ListBean createFromParcel(Parcel source) {
                    return new ListBean(source);
                }

                @Override
                public ListBean[] newArray(int size) {
                    return new ListBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.name);
            dest.writeList(this.list);
        }

        public ListBeanX() {
        }

        protected ListBeanX(Parcel in) {
            this.id = in.readString();
            this.name = in.readString();
            this.list = new ArrayList<ListBean>();
            in.readList(this.list, ListBean.class.getClassLoader());
        }

        public static final Creator<ListBeanX> CREATOR = new Creator<ListBeanX>() {
            @Override
            public ListBeanX createFromParcel(Parcel source) {
                return new ListBeanX(source);
            }

            @Override
            public ListBeanX[] newArray(int size) {
                return new ListBeanX[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeList(this.list);
    }

    public AddressEntity() {
    }

    protected AddressEntity(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.list = new ArrayList<ListBeanX>();
        in.readList(this.list, ListBeanX.class.getClassLoader());
    }

    public static final Parcelable.Creator<AddressEntity> CREATOR = new Parcelable.Creator<AddressEntity>() {
        @Override
        public AddressEntity createFromParcel(Parcel source) {
            return new AddressEntity(source);
        }

        @Override
        public AddressEntity[] newArray(int size) {
            return new AddressEntity[size];
        }
    };
}
