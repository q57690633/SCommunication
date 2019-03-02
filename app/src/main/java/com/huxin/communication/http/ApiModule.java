package com.huxin.communication.http;


import com.huxin.communication.HuXinApplication;
import com.huxin.communication.entity.AddUserInformationEntity;
import com.huxin.communication.entity.AddressBookEntity;
import com.huxin.communication.entity.AliPayEntity;
import com.huxin.communication.entity.AppPayEntity;
import com.huxin.communication.entity.AreaOneScreenEntity;
import com.huxin.communication.entity.AreaTwoScreenEntity;
import com.huxin.communication.entity.AroundStickEntity;
import com.huxin.communication.entity.AroundTravelEntity;
import com.huxin.communication.entity.BuyerScreeningEntity;
import com.huxin.communication.entity.CaixianForeignTravelEntity;
import com.huxin.communication.entity.CollectAroundEntity;
import com.huxin.communication.entity.CollectEntity;
import com.huxin.communication.entity.CollectForeignEntity;
import com.huxin.communication.entity.CollectTicketEntity;
import com.huxin.communication.entity.CountyEntity;
import com.huxin.communication.entity.ForeignCityEntity;
import com.huxin.communication.entity.ForeignNationEntity;
import com.huxin.communication.entity.ForeignSpotEntity;
import com.huxin.communication.entity.ForeignStickEntity;
import com.huxin.communication.entity.ForeignTraveAroundEntity;
import com.huxin.communication.entity.ForeignTravelEntity;
import com.huxin.communication.entity.ForgetPasswordEntity;
import com.huxin.communication.entity.HomeEntity;
import com.huxin.communication.entity.HomeTravelEntity;
import com.huxin.communication.entity.InformationDetailEntity;
import com.huxin.communication.entity.InlandCityEntity;
import com.huxin.communication.entity.InlandSpotEntity;
import com.huxin.communication.entity.InvitationEntity;
import com.huxin.communication.entity.LoginEntity;
import com.huxin.communication.entity.MatchingProductEntity;
import com.huxin.communication.entity.PersonProductEntity;
import com.huxin.communication.entity.ProvinceEntity;
import com.huxin.communication.entity.RegisterEntity;
import com.huxin.communication.entity.RemoveCollectEntity;
import com.huxin.communication.entity.RentalScreeningEntity;
import com.huxin.communication.entity.SaleOfScreeningEntity;
import com.huxin.communication.entity.SelectByLikeEntity;
import com.huxin.communication.entity.SelectPlotEntity;
import com.huxin.communication.entity.SelectTabEntity;
import com.huxin.communication.entity.TabTravelNameEntity;
import com.huxin.communication.entity.TicketInfoEntity;
import com.huxin.communication.entity.TicketStickEntity;
import com.huxin.communication.entity.ToVipEntity;
import com.huxin.communication.entity.TopSelectionEntity;
import com.huxin.communication.entity.UpdateUserInformationEntity;
import com.huxin.communication.entity.UpdateUserPhoneEntity;
import com.huxin.communication.entity.WantedScreeningEntity;
import com.huxin.communication.http.service.ApiFactory;
import com.huxin.communication.ui.LoginActivity;
import com.huxin.communication.utils.PreferenceUtil;
import com.sky.kylog.KyLog;

import java.util.List;

import retrofit2.http.Field;
import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by kyosky on 16/1/12.
 */
public class ApiModule {
    private static final String ApiKey = "App_Android";
    public static final String TOKEN = "token";
    public static final String UID = "uid";


    private static ApiModule apiModule;

    private ApiModule() {
    }

    public static ApiModule getInstance() {
        if (apiModule == null) {
            synchronized (ApiModule.class) {
                if (apiModule == null) {
                    apiModule = new ApiModule();
                }
            }
        }
        return apiModule;
    }

    /**
     * //     * 注册接口
     * //     * <p>
     * //     * 参数
     * //     * {
     * //     * "phone": "18518167049",
     * //     * "verifyCode": "123456"
     * //     * }
     * //     *
     * //     * @return List Login
     * //
     */
    public Observable<RegisterEntity> registers(int registerType, String phone, String authCode, String password) {
        return ApiFactory.getFactory().BaiHangTongYeService().Register(registerType, phone, authCode, password)
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 发送验证码
     *
     * @return
     */
    public Observable<ResponseUntil> sendMessage(String phone) {
        return ApiFactory.getFactory().BaiHangTongYeService().sendMessage(phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 登录
     *
     * @param phone
     * @param password
     * @return
     */
    public Observable<LoginEntity> logins(String phone, String password) {
        return ApiFactory.getFactory().BaiHangTongYeService().logins(phone, password)
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 忘记密码
     *
     * @param phone
     * @param password
     * @return
     */
    public Observable<ForgetPasswordEntity> forgetPasswords(String phone, String password, String authCode) {
        return ApiFactory.getFactory().BaiHangTongYeService().forgetPassword(phone, password, authCode)
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 完善用户信息
     *
     * @return
     */
    public Observable<AddUserInformationEntity> updatUserInformation(String province, String city,
                                                                     String county, String companyName,
                                                                     String licenseCode, String position,
                                                                     String industryType, String invitationCode1, String invitationCode2,
                                                                     String cOrP, String uid, String token, String phone) {
        return ApiFactory.getFactory().BaiHangTongYeService().addUserInformation(province, city, county, companyName,
                licenseCode, position, industryType, invitationCode1, invitationCode2, cOrP, uid, token, phone)
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 更新用户信息
     *
     * @return
     */
    public Observable<UpdateUserInformationEntity> updateUserInformation(String username, String area,
                                                                         String storeName, String position,
                                                                         String industryType, String uid, String token) {
        return ApiFactory.getFactory().BaiHangTongYeService().updateUserInformation(username, area, storeName, position, industryType, uid, token)
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 出售筛选
     *
     * @return
     */
    public Observable<SaleOfScreeningEntity> SaleOfScreening(String villageName,
                                                             String houseType, String minAcreage,
                                                             String maxAcreage, String minPrice,
                                                             String maxPrice, String orientation,
                                                             String permit, String fitment,
                                                             String element, String floorAge,
                                                             String purpose, String ownership,
                                                             String productType,
                                                             String newOrOld,
                                                             String city, String areaOne,
                                                             String curPage, String houseHoldAppliances) {
        return ApiFactory.getFactory().BaiHangTongYeService().SaleOfScreening(villageName,
                houseType, minAcreage,
                maxAcreage, minPrice, maxPrice, orientation,
                permit, fitment, element, floorAge,
                purpose, ownership,
                productType,
                newOrOld, PreferenceUtil.getString(TOKEN),
                city, areaOne,
                curPage, houseHoldAppliances)
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 出租筛选
     *
     * @return
     */
    public Observable<RentalScreeningEntity> rentalScreening(String villageName,
                                                             String houseType, String minAcreage,
                                                             String maxAcreage, String minPrice,
                                                             String maxPrice, String orientation,
                                                             String permit, String fitment,
                                                             String element, String floorAge,
                                                             String purpose, String ownership,
                                                             String productType,
                                                             String newOrOld,
                                                             String city, String areaOne,
                                                             String curPage, String houseHoldAppliances) {
        return ApiFactory.getFactory().BaiHangTongYeService().rentalScreening(villageName,
                houseType, minAcreage,
                maxAcreage, minPrice, maxPrice, orientation,
                permit, fitment, element, floorAge,
                purpose, ownership,
                productType,
                newOrOld, PreferenceUtil.getString(TOKEN),
                city, areaOne,
                curPage, houseHoldAppliances)
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<BuyerScreeningEntity> buyerScreening(String villageName,
                                                           String houseType, String minAcreage,
                                                           String maxAcreage, String minPrice,
                                                           String maxPrice, String orientation,
                                                           String permit, String fitment,
                                                           String element, String floorAge,
                                                           String purpose, String ownership,
                                                           String productType,
                                                           String newOrOld,
                                                           String city, String areaOne,
                                                           String curPage, String houseHoldAppliances) {
        return ApiFactory.getFactory().BaiHangTongYeService().buyerScreening(villageName,
                houseType, minAcreage,
                maxAcreage, minPrice, maxPrice, orientation,
                permit, fitment, element, floorAge,
                purpose, ownership,
                productType,
                newOrOld, PreferenceUtil.getString(TOKEN),
                city, areaOne,
                curPage, houseHoldAppliances)
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 求租筛选
     *
     * @return
     */
    public Observable<WantedScreeningEntity> wantedScreening(String villageName,
                                                             String houseType, String minAcreage,
                                                             String maxAcreage, String minPrice,
                                                             String maxPrice, String orientation,
                                                             String permit, String fitment,
                                                             String element, String floorAge,
                                                             String purpose, String ownership,
                                                             String productType,
                                                             String newOrOld,
                                                             String city, String areaOne,
                                                             String curPage, String houseHoldAppliances) {
        return ApiFactory.getFactory().BaiHangTongYeService().wantedScreening(villageName,
                houseType, minAcreage,
                maxAcreage, minPrice, maxPrice, orientation,
                permit, fitment, element, floorAge,
                purpose, ownership,
                productType,
                newOrOld, PreferenceUtil.getString(TOKEN),
                city, areaOne,
                curPage, houseHoldAppliances)
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 出售、出租商品详情
     *
     * @return
     */
    public Observable<List<InformationDetailEntity>> getInformation(String pid,
                                                                    String productType) {
        return ApiFactory.getFactory().BaiHangTongYeService().getInformation(pid, productType, PreferenceUtil.getString(TOKEN))
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 求购、求租商品详情
     *
     * @return
     */
    public Observable<InformationDetailEntity> getQiuZuInformation(String pid,
                                                                   String productType) {
        return ApiFactory.getFactory().BaiHangTongYeService().getQiuZuInformation(pid, productType, PreferenceUtil.getString(TOKEN))
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 收藏列表
     *
     * @return
     */
    public Observable<CollectEntity> getCollectProduct(String villageName,
                                                             String minAcreage, String maxAcreage,
                                                             String houseType, String minPrice,
                                                             String maxPrice, String element,
                                                             String newOrOld, String orientation,
                                                             String houseHoldAppliances, String fitment,
                                                             String permit, String purpose,
                                                             String ownership, String floorAge,
                                                             String productType, String curPage) {
        return ApiFactory.getFactory().BaiHangTongYeService().getCollectProduct(String.valueOf(PreferenceUtil.getInt(UID)), villageName,
                minAcreage, maxAcreage, houseType, minPrice, maxPrice, element, newOrOld, orientation, houseHoldAppliances, fitment,
                permit, purpose, ownership, floorAge, productType, PreferenceUtil.getString(TOKEN), curPage)
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 个人数据库数据查询
     *
     * @return
     */
    public Observable<PersonProductEntity> getPersonProduct(String villageName,
                                                            String minAcreage, String maxAcreage,
                                                            String houseType, String minPrice,
                                                            String maxPrice, String element,
                                                            String newOrOld, String orientation,
                                                            String houseHoldAppliances, String fitment,
                                                            String permit, String purpose,
                                                            String ownership, String floorAge,
                                                            String productType, String curPage) {
        return ApiFactory.getFactory().BaiHangTongYeService().getPersonProduct(String.valueOf(PreferenceUtil.getInt(UID)), villageName,
                minAcreage, maxAcreage, houseType, minPrice, maxPrice, element, newOrOld, orientation, houseHoldAppliances, fitment,
                permit, purpose, ownership, floorAge, productType, PreferenceUtil.getString(TOKEN), curPage)
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 房客匹配
     *
     * @return
     */
    public Observable<List<MatchingProductEntity>> matchingProduct(String uid, String productType) {
        return ApiFactory.getFactory().BaiHangTongYeService().matchingProduct(uid, productType, PreferenceUtil.getString(TOKEN))
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 邀请码展示
     *
     * @return
     */
    public Observable<InvitationEntity> matchingProducts() {
        return ApiFactory.getFactory().BaiHangTongYeService().selectInvitation(PreferenceUtil.getInt(UID), PreferenceUtil.getString(TOKEN))
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 添加到收藏夹
     *
     * @return
     */
    public Observable<Response> addCollectTravel(String pid, int productType) {
        return ApiFactory.getFactory().BaiHangTongYeService().addCollectTravel(PreferenceUtil.getInt(UID), pid, productType, PreferenceUtil.getString(TOKEN))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 移出收藏夹
     *
     * @return
     */
    public Observable<RemoveCollectEntity> removeCollectProduct(String pid, String productType) {
        return ApiFactory.getFactory().BaiHangTongYeService().removeCollectProduct(String.valueOf(PreferenceUtil.getInt(UID)), pid, productType, PreferenceUtil.getString(TOKEN))
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 删除个人数据
     *
     * @return
     */
    public Observable<Response> deletePersonProduct(String pid, String productType) {
        return ApiFactory.getFactory().BaiHangTongYeService().deletePersonProduct(pid, productType, PreferenceUtil.getString(TOKEN))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 置顶精选查询
     *
     * @return
     */
    public Observable<List<TopSelectionEntity>> selectStick(String villageName,
                                                            String houseType, String minAcreage,
                                                            String maxAcreage, String minPrice,
                                                            String maxPrice, String orientation,
                                                            String permit, String fitment,
                                                            String element, String floorAge,
                                                            String purpose, String ownership,
                                                            String productType,
                                                            String newOrOld,
                                                            String city, String areaOne,
                                                            String curPage, String houseHoldAppliances) {
        return ApiFactory.getFactory().BaiHangTongYeService().selectStick(villageName,
                houseType, minAcreage,
                maxAcreage, minPrice, maxPrice, orientation,
                permit, fitment, element, floorAge,
                purpose, ownership,
                productType,
                newOrOld, PreferenceUtil.getString(TOKEN),
                city, areaOne,
                curPage, houseHoldAppliances)
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 个人数据库修改
     *
     * @return
     */
    public Observable<Response> updatePersonProduct(String id, String productType, String acreage,
                                                    String totalPrice, String villageName,
                                                    String floorNumber, String totalFloorNumber,
                                                    String newOrOld, String loans,
                                                    String houseHoldAppliances, String keying,
                                                    String fitment, String orientation,
                                                    String title, String permit,
                                                    String purpose, String stick,
                                                    String tabId, String files,
                                                    String unlimitedEstate, String minPrice,
                                                    String maxPrice, String minAcreage,
                                                    String maxAcreage, String uid) {
        return ApiFactory.getFactory().BaiHangTongYeService().updatePersonProduct(id, productType,
                PreferenceUtil.getString(TOKEN), acreage, totalPrice, villageName, floorNumber, totalFloorNumber, newOrOld, loans,
                houseHoldAppliances, keying, fitment, orientation, title, permit, purpose, stick, tabId, files, unlimitedEstate, minPrice, maxPrice, minAcreage, maxAcreage, uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 商品转发
     * s
     *
     * @return
     */
    public Observable<Response> matchingsProduct(String id, String productType) {
        return ApiFactory.getFactory().BaiHangTongYeService().matchingProduct(id, productType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 删除个人数据
     *
     * @return
     */
    public Observable<Response> deletePersonProducts(String pid, String productType) {
        return ApiFactory.getFactory().BaiHangTongYeService().deletePersonProduct(pid, productType, PreferenceUtil.getString(TOKEN))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 房产标签查询
     *
     * @return
     */
    public Observable<SelectTabEntity> selectTab(String productType) {
        return ApiFactory.getFactory().BaiHangTongYeService().selectTab(productType, String.valueOf(PreferenceUtil.getInt(UID)), PreferenceUtil.getString(TOKEN))
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 添加出售信息
     *
     * @return
     */
    public Observable<Response> addSaleProduct(String villageName, String acreage,
                                               String houseType, String totalPrice,
                                               String floorNumber, String totalFloorNumber,
                                               String newOrOld, String loans,
                                               String keying, String houseHoldAppliances,
                                               String fitment, String permit,
                                               String orientation, String purpose,
                                               String title,
                                               String stick, String exclusive,
                                               String houseNumber, String pdu,
                                               String floorSize, String tabId) {
        return ApiFactory.getFactory().BaiHangTongYeService().addSaleProduct(villageName, acreage, houseType, totalPrice, floorNumber, totalFloorNumber,
                newOrOld, loans, keying, houseHoldAppliances, fitment, permit, orientation, purpose, title, String.valueOf(PreferenceUtil.getInt(UID)),
                stick, exclusive, houseNumber, pdu, floorSize, tabId, PreferenceUtil.getString(TOKEN))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 添加出租信息
     *
     * @return
     */
    public Observable<Response> addRentProduct(String villageName, String acreage,
                                               String houseType, String price,
                                               String floorNumber, String totalFloorNumber,
                                               String fitment, String keying,
                                               String paymentType,
                                               String title,
                                               String stick, String tabId,
                                               String exclusive, String purpose,
                                               String houseHoldAppliances, String orientation,
                                               String houseNumber, String floorSize,
                                               String pdu) {
        return ApiFactory.getFactory().BaiHangTongYeService().addRentProduct(villageName, acreage,
                houseType, price,
                floorNumber, totalFloorNumber,
                fitment, keying,
                paymentType,
                title, String.valueOf(PreferenceUtil.getInt(UID)),
                stick, tabId,
                exclusive, purpose,
                houseHoldAppliances, orientation,
                houseNumber, floorSize,
                pdu, PreferenceUtil.getString(TOKEN))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 添加求购信息
     *
     * @return
     */
    public Observable<Response> addBuyerProduct(String villageName, String unlimitedEstate,
                                                String minPrice, String totalPrice,
                                                String newOrOld, String maxAcreage,
                                                String houseType, String floorAge,
                                                String permit, String remark, String stick,
                                                String tabId) {
        return ApiFactory.getFactory().BaiHangTongYeService().addBuyerProduct(villageName, unlimitedEstate, minPrice, totalPrice, newOrOld, maxAcreage, houseType, floorAge,
                permit, remark, String.valueOf(PreferenceUtil.getInt(UID)), stick, tabId, PreferenceUtil.getString(TOKEN))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 添加求租信息
     *
     * @return
     */
    public Observable<Response> addWantedProduct(String villageName, String unlimitedEstate,
                                                 String minPrice, String totalPrice,
                                                 String newOrOld, String maxAcreage,
                                                 String houseType, String fitment,
                                                 String paymentType, String householdAppliances,
                                                 String remark, String stick,
                                                 String tabId) {
        return ApiFactory.getFactory().BaiHangTongYeService().addWantedProduct(villageName, unlimitedEstate, minPrice, totalPrice, newOrOld, maxAcreage, houseType, fitment,
                paymentType, householdAppliances, remark, String.valueOf(PreferenceUtil.getInt(UID)), stick, tabId, PreferenceUtil.getString(TOKEN))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 首页数据
     *
     * @return
     */
    public Observable<HomeEntity> getHomes(String city, String areaOne) {
        return ApiFactory.getFactory().BaiHangTongYeService().getHome(city, areaOne, PreferenceUtil.getString(TOKEN))
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 省
     *
     * @return
     */
    public Observable<List<ProvinceEntity>> getProvinces() {
        return ApiFactory.getFactory().BaiHangTongYeService().getProvinces(PreferenceUtil.getString(TOKEN))
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 市
     *
     * @return
     */
    public Observable<List<InlandCityEntity>> getInlandCity(String province_code) {
        return ApiFactory.getFactory().BaiHangTongYeService().getInlandCity(PreferenceUtil.getString(TOKEN), province_code)
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 区
     *
     * @return
     */
    public Observable<List<CountyEntity>> getCountys() {
        return ApiFactory.getFactory().BaiHangTongYeService().getCountys()
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 更新注册电话
     *
     * @return
     */
    public Observable<UpdateUserPhoneEntity> updateUserPhone(String phone, String authCode,
                                                             String secondPhone, String secondAuthCode,
                                                             String uid, String token) {
        return ApiFactory.getFactory().BaiHangTongYeService().updateUserPhone(phone, authCode, secondPhone, secondAuthCode, uid, token)
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 周边游和国内游筛选和搜索
     *
     * @return
     */
    public Observable<AroundTravelEntity> gettingAroundTravel(String depart_code, String goalsId,
                                                              String sort_type, String tOtherId,
                                                              String tActivityId, String tStayId,
                                                              String tAddressId, String tTrafficId,
                                                              String tConsumeId, String minPri_maxPri,
                                                              String numberDays, String keyWord,
                                                              String curPage, String minDay, String maxDay, String uid,
                                                              String travel_kind, String lineOrThrow) {
        return ApiFactory.getFactory().BaiHangTongYeService().gettingAroundTravel(depart_code, goalsId,
                sort_type, tOtherId, tActivityId, tStayId, tAddressId, tTrafficId, tConsumeId, minPri_maxPri,
                numberDays, PreferenceUtil.getString(TOKEN), keyWord, curPage, minDay, maxDay, uid, travel_kind, lineOrThrow)
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 国外游筛选和搜索
     *
     * @return
     */
    public Observable<ForeignTravelEntity> gettingForeignTravel(String depart_name, String min_days,
                                                                String max_days, String spot_name,
                                                                String goals_name, String t_activity_id,
                                                                String t_stay_id, String t_other_id,
                                                                String t_address_id, String t_traffic_id,
                                                                String t_overseas_id,
                                                                String t_consume_id, String sort_type,
                                                                String minPri_maxPri, String number_days,
                                                                String keyWord, String curPage, String uid,
                                                                String line_or_throw) {
        return ApiFactory.getFactory().BaiHangTongYeService().gettingForeignTravel(depart_name, min_days,
                max_days, spot_name, goals_name, t_activity_id, t_stay_id, t_other_id, t_address_id,
                t_traffic_id, t_overseas_id, t_consume_id, sort_type, minPri_maxPri, number_days,
                PreferenceUtil.getString(TOKEN), keyWord, curPage, uid, line_or_throw)
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 票务筛选
     *
     * @return
     */
    public Observable<TicketInfoEntity> getTicketInfo(String ticket_type, String ticket_city_name,
                                                      String minPri_maxPri, String ticket_theme_id,
                                                      String ticket_activity_id, String ticket_other_id,
                                                      String sort_type,
                                                      String keyWord, String curPage, String uid) {
        return ApiFactory.getFactory().BaiHangTongYeService().getTicketInfo(ticket_type, ticket_city_name,
                minPri_maxPri, ticket_theme_id, ticket_activity_id, ticket_other_id, sort_type,
                PreferenceUtil.getString(TOKEN), keyWord, curPage, uid)
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 踩线或甩位-国外
     *
     * @return
     */
    public Observable<CaixianForeignTravelEntity> gettingCaiXianForeignTravel(String line_or_throw, String number_days, String depart_code,
                                                                              String min_days, String max_days,
                                                                              String spot_name, String goals_name,
                                                                              String t_activity_id,
                                                                              String t_stay_id, String t_other_id,
                                                                              String t_address_id, String t_traffic_id,
                                                                              String t_overseas_id, String minPri_maxPri,
                                                                              String t_consume_id, String sort_type) {
        return ApiFactory.getFactory().BaiHangTongYeService().gettingCaiXianForeignTravel(line_or_throw, number_days,
                PreferenceUtil.getString(TOKEN), depart_code, min_days, max_days, spot_name, goals_name, t_activity_id, t_stay_id, t_other_id, t_address_id, t_traffic_id, t_overseas_id, minPri_maxPri, t_consume_id, sort_type)
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 踩线或甩位-国内
     *
     * @return
     */
    public Observable<ForeignTraveAroundEntity> gettingCaiXianAroundTravel(String line_or_throw, String maxDay,
                                                                           String minPri_maxPri, String numberDays,
                                                                           String minDay, String depart_code,
                                                                           String goalsId,
                                                                           String sort_type, String tOtherId,
                                                                           String tActivityId, String tStayId,
                                                                           String tAddressId, String tTrafficId,
                                                                           String tConsumeId) {
        return ApiFactory.getFactory().BaiHangTongYeService().gettingCaiXianAroundTravel(line_or_throw,
                PreferenceUtil.getString(TOKEN), maxDay, minPri_maxPri, numberDays,
                minDay, depart_code, goalsId, sort_type, tOtherId, tActivityId, tStayId, tAddressId, tTrafficId,
                tConsumeId)
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 首页
     *
     * @return
     */
    public Observable<HomeTravelEntity> getTravelHome(String city_name) {
        return ApiFactory.getFactory().BaiHangTongYeService().getTravelHome(city_name, PreferenceUtil.getString(TOKEN))
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 发布周边游和国内游
     *
     * @return
     */
    public Observable<ResponseUntil> issueAroundRoute(String depart_code, String depart_pro_code,
                                                      String goalsId, String spotName,
                                                      String numberDays, String totalPrice,
                                                      String finalPrice, String returnPrice,
                                                      String pickupPrice,
                                                      String totalPriceChild, String finalPriceChild,
                                                      String returnPriceChild, String tAddressId,
                                                      String tTrafficId, String tConsumeId,
                                                      String tActivityId, String tStayId,
                                                      String tOtherId, String travelTitle,
                                                      String generalize, String stick, String lineOrThrow, String files,
                                                      String stick_new, String stick_low,
                                                      String stick_better, String stick_throw,
                                                      String stick_rate, String stick_return,
                                                      String stick_hot, String stick_zeroC,
                                                      String goals_city, String goals_pro,
                                                      String goals_city_code, String depart_name,String travel_kind) {
        return ApiFactory.getFactory().BaiHangTongYeService().issueAroundRoute(depart_code, depart_pro_code,
                goalsId, spotName, numberDays, totalPrice, finalPrice, returnPrice, pickupPrice, totalPriceChild, finalPriceChild, returnPriceChild, tAddressId, tTrafficId, tConsumeId,
                tActivityId, tStayId, tOtherId, travelTitle, generalize, stick, String.valueOf(PreferenceUtil.getInt(UID)), lineOrThrow, PreferenceUtil.getString(TOKEN), files, stick_new, stick_low,
                stick_better, stick_throw, stick_rate, stick_return, stick_hot, stick_zeroC, goals_city, goals_pro, goals_city_code, depart_name,travel_kind)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());


    }

    /**
     * 发布国外游
     *
     * @return
     */
    public Observable<ResponseUntil> issueForeignRoute(String depart_name, String depart_pro_name,
                                                  String goals_nat_name, String goals_name,
                                                  String spot_name, String stick, String line_or_throw,
                                                  String number_days, String total_price,
                                                  String final_price, String return_price,
                                                  String pickup_price, String total_price_child,
                                                  String final_price_child, String return_price_child,
                                                  String t_address_id, String t_traffic_id,
                                                  String t_consume_id, String t_activity_id,
                                                  String t_stay_id, String t_other_id,
                                                  String travel_title, String generalize,
                                                  String files, String stick_new,
                                                  String stick_low, String stick_better,
                                                  String stick_throw, String stick_rate,
                                                  String stick_return, String stick_hot,
                                                  String stick_zeroC) {
        return ApiFactory.getFactory().BaiHangTongYeService().issueForeignRoute(depart_name, depart_pro_name,
                goals_nat_name, goals_name, spot_name, stick, String.valueOf(PreferenceUtil.getInt(UID)), line_or_throw, PreferenceUtil.getString(TOKEN), number_days, total_price, final_price, return_price,
                pickup_price, total_price_child, final_price_child, return_price_child, t_address_id, t_traffic_id, t_consume_id, t_activity_id, t_stay_id, t_other_id, travel_title, generalize,
                files, stick_new, stick_low, stick_better, stick_throw, stick_rate, stick_return, stick_hot, stick_zeroC)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 发布票务
     *
     * @return
     */
    public Observable<ResponseUntil> issueTicketForeignRoute(String ticket_pro_name, String ticket_city_name,
                                                        String ticket_name, String ticket_addr,
                                                        String ticket_type, String open_time,
                                                        String original_price, String final_price,
                                                        String original_price_child,
                                                        String final_price_child, String original_price_evening,
                                                        String final_price_evening, String original_price_parent_child,
                                                        String final_price_parent_child, String original_price_family,
                                                        String final_price_family, String original_boat,
                                                        String final_boat, String original_car,
                                                        String final_car, String ticket_theme_id,
                                                        String ticket_activity_id, String ticket_other_id, String stick,
                                                        String line_or_throw, String files, String stick_new,
                                                        String stick_low, String stick_better,
                                                        String stick_throw, String stick_rate,
                                                        String stick_return, String stick_hot, String stick_zeroC,
                                                        String generalize, String original_price_total,
                                                        String final_price_total, String ticket_pro_code) {
        return ApiFactory.getFactory().BaiHangTongYeService().issueTicketForeignRoute(ticket_pro_name, ticket_city_name,
                ticket_name, ticket_addr, ticket_type, open_time, original_price, final_price, original_price_child, final_price_child, original_price_evening, final_price_evening, original_price_parent_child,
                final_price_parent_child, original_price_family, final_price_family, original_boat, final_boat, original_car, final_car, ticket_theme_id, ticket_activity_id, ticket_other_id, String.valueOf(PreferenceUtil.getInt(UID)), stick, line_or_throw,
                files, PreferenceUtil.getString(TOKEN), stick_new, stick_low, stick_better, stick_throw, stick_rate, stick_return, stick_hot, stick_zeroC, generalize, original_price_total, final_price_total, ticket_pro_code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 查询标签
     *
     * @return
     */
    public Observable<TabTravelNameEntity> selectTravelTab(String tag_kind) {
        return ApiFactory.getFactory().BaiHangTongYeService().selectTab(tag_kind, PreferenceUtil.getString(TOKEN))
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 根据城市查目的地景点
     *
     * @return
     */
    public Observable<List<InlandSpotEntity>> getInlandSpot(String city_code) {
        return ApiFactory.getFactory().BaiHangTongYeService().getInlandSpot(city_code, PreferenceUtil.getString(TOKEN))
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 国外游-获得所有国家信息接口
     *
     * @return
     */
    public Observable<List<ForeignNationEntity>> getForeignNation() {
        return ApiFactory.getFactory().BaiHangTongYeService().getForeignNation(PreferenceUtil.getString(TOKEN))
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获得所有国外城市信息接口
     *
     * @return
     */
    public Observable<List<ForeignCityEntity>> getForeignCity(String nation_name) {
        return ApiFactory.getFactory().BaiHangTongYeService().getForeignCity(PreferenceUtil.getString(TOKEN), nation_name)
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获得所有景点信息接口(根据城市名)
     *
     * @return
     */
    public Observable<List<ForeignSpotEntity>> getForeignSpot(String city_name) {
        return ApiFactory.getFactory().BaiHangTongYeService().getForeignSpot(PreferenceUtil.getString(TOKEN), city_name)
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 个人收藏查询-国内和周边
     *
     * @return
     */
    public Observable<CollectAroundEntity> getCollectAround(String depart_code,
                                                            String goalsId, String sort_type,
                                                            String tOtherId, String tActivityId,
                                                            String tStayId, String tAddressId,
                                                            String tTrafficId,
                                                            String tConsumeId, String minPri_maxPri,
                                                            String numberDays,
                                                            String keyWord, String curPage,
                                                            String minDay, String maxDay
    ) {
        return ApiFactory.getFactory().BaiHangTongYeService().getCollectAround(depart_code,
                goalsId, sort_type, tOtherId, tActivityId, tStayId, tAddressId, tTrafficId, tConsumeId,
                minPri_maxPri, numberDays, PreferenceUtil.getString(TOKEN), keyWord, curPage,
                minDay, maxDay, String.valueOf(PreferenceUtil.getInt(UID)))
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 个人收藏查询-境外
     *
     * @return
     */
    public Observable<CollectForeignEntity> getCollectForeign(String depart_code, String spot_name,
                                                              String goals_name, String sort_type,
                                                              String tOtherId, String tActivityId,
                                                              String tStayId, String tAddressId,
                                                              String tTrafficId, String t_overseas_id,
                                                              String tConsumeId, String minPri_maxPri,
                                                              String numberDays,
                                                              String keyWord, String curPage,
                                                              String minDay, String maxDay
    ) {
        return ApiFactory.getFactory().BaiHangTongYeService().getCollectForeign(depart_code, spot_name,
                goals_name, sort_type, tOtherId, tActivityId, tStayId, tAddressId, tTrafficId, t_overseas_id,
                tConsumeId, minPri_maxPri, numberDays, PreferenceUtil.getString(TOKEN), keyWord, curPage, minDay, maxDay, String.valueOf(PreferenceUtil.getInt(UID)))
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 个人收藏查询-票务
     *
     * @return
     */
    public Observable<CollectTicketEntity> getCollectTicket(String ticket_type,
                                                            String ticket_city_name, String minPri_maxPri,
                                                            String ticket_theme_id, String ticket_activity_id,
                                                            String ticket_other_id, String sort_type,
                                                            String keyWord,
                                                            String curPage) {
        return ApiFactory.getFactory().BaiHangTongYeService().getCollectTicket(ticket_type,
                ticket_city_name, minPri_maxPri, ticket_theme_id, ticket_activity_id, ticket_other_id, sort_type,
                PreferenceUtil.getString(TOKEN), keyWord, String.valueOf(PreferenceUtil.getInt(UID)), curPage)
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 旅游-添加到收藏夹
     *
     * @return
     */
    public Observable<ResponseUntil> addTravelCollect(String ids, int travelType) {
        return ApiFactory.getFactory().BaiHangTongYeService().addTravelCollect(PreferenceUtil.getInt(UID), ids, travelType, PreferenceUtil.getString(TOKEN))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 周边游和国内游置顶精选
     *
     * @return
     */
    public Observable<AroundStickEntity> selectAroundStick(String depart_code, String goalsId,
                                                           String sort_type, String tOtherId,
                                                           String tActivityId, String tStayId,
                                                           String tAddressId, String tTrafficId,
                                                           String tConsumeId, String minPri_maxPri,
                                                           String numberDays, String keyWord,
                                                           String curPage, String minDay, String maxDay, String uid) {
        return ApiFactory.getFactory().BaiHangTongYeService().selectAroundStick(depart_code, goalsId,
                sort_type, tOtherId, tActivityId, tStayId, tAddressId, tTrafficId, tConsumeId, minPri_maxPri,
                numberDays, PreferenceUtil.getString(TOKEN), keyWord, curPage, minDay, maxDay, uid, "1")
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 国外游置顶精选
     *
     * @return
     */
    public Observable<ForeignStickEntity> selectForeignStick(String depart_name, String min_days,
                                                             String max_days, String spot_name,
                                                             String goals_name, String t_activity_id,
                                                             String t_stay_id, String t_other_id,
                                                             String t_address_id, String t_traffic_id,
                                                             String t_overseas_id,
                                                             String t_consume_id, String sort_type,
                                                             String minPri_maxPri, String number_days,
                                                             String keyWord, String curPage, String uid) {
        return ApiFactory.getFactory().BaiHangTongYeService().selectForeignStick(depart_name, min_days,
                max_days, spot_name, goals_name, t_activity_id, t_stay_id, t_other_id, t_address_id, t_traffic_id,
                t_overseas_id, t_consume_id, sort_type, minPri_maxPri, number_days, PreferenceUtil.getString(TOKEN),
                keyWord, curPage, uid, "1")
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 票务置顶精选
     *
     * @return
     */
    public Observable<TicketStickEntity> selectTicketStick(String ticket_type, String ticket_city_name,
                                                           String minPri_maxPri, String ticket_theme_id,
                                                           String ticket_activity_id, String ticket_other_id,
                                                           String sort_type,
                                                           String keyWord, String curPage, String uid) {
        return ApiFactory.getFactory().BaiHangTongYeService().selectTicketStick(ticket_type, ticket_city_name,
                minPri_maxPri, ticket_theme_id, ticket_activity_id, ticket_other_id, sort_type, PreferenceUtil.getString(TOKEN), keyWord, curPage, uid, "1")
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 跳转到vip
     *
     * @return
     */
    public Observable<ToVipEntity> toVip(String productType) {
        return ApiFactory.getFactory().BaiHangTongYeService().toVip(PreferenceUtil.getString(TOKEN), productType)
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 小区搜索
     *
     * @return
     */
    public Observable<List<SelectPlotEntity>> selectPlot(String areaSecondId, String areaId) {
        return ApiFactory.getFactory().BaiHangTongYeService().selectPlot(areaSecondId, areaId, PreferenceUtil.getString(TOKEN))
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 灏忓尯妯＄硦鏌ヨ
     *
     * @return
     */
    public Observable<List<SelectByLikeEntity>> selectByLike(String villageName) {
        return ApiFactory.getFactory().BaiHangTongYeService().selectByLike(villageName,PreferenceUtil.getString(TOKEN),String.valueOf(PreferenceUtil.getInt(UID)))
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 娣诲姞灏忓尯
     *
     * @return
     */
    public Observable<Response> addPlot( String city, String areaOne,
                                         String areaTwo, String villageName) {
        return ApiFactory.getFactory().BaiHangTongYeService().addPlot(city, areaOne, areaTwo,villageName,PreferenceUtil.getString(TOKEN))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 下属区域筛选
     *
     * @return
     */
    public Observable<List<AreaTwoScreenEntity>> areaTwoScreen(String areaId) {
        return ApiFactory.getFactory().BaiHangTongYeService().areaTwoScreen(PreferenceUtil.getString(TOKEN), areaId)
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 城区筛选
     *
     * @return
     */
    public Observable<List<AreaOneScreenEntity>> areaOneScreen(String city) {
        return ApiFactory.getFactory().BaiHangTongYeService().areaOneScreen(PreferenceUtil.getString(TOKEN), city)
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 更新个人数据库
     *
     * @return
     */
    public Observable<Response<AroundTravelEntity>> updatePersonageTravel(String depart_code, String depart_pro_code,
                                                      String goalsId, String spotName,
                                                      String numberDays, String totalPrice,
                                                      String finalPrice, String returnPrice,
                                                      String pickupPrice,
                                                      String totalPriceChild, String finalPriceChild,
                                                      String returnPriceChild, String tAddressId,
                                                      String tTrafficId, String tConsumeId,
                                                      String tActivityId, String tStayId,
                                                      String final_boat, String travelTitle,
                                                      String final_car, String generalize,
                                                      String stick,
                                                      String lineOrThrow, String stick_new,
                                                      String stick_low, String stick_better,
                                                      String stick_throw, String stick_rate,
                                                      String stick_return, String stick_hot, String stick_zeroC,
                                                      String goals_city, String goals_pro,
                                                      String goals_city_code, String id) {
        return ApiFactory.getFactory().BaiHangTongYeService().updatePersonageTravel(depart_code, depart_pro_code,
                goalsId, spotName, numberDays, totalPrice, finalPrice, returnPrice, pickupPrice, totalPriceChild, finalPriceChild,
                returnPriceChild, tAddressId, tTrafficId, tConsumeId, tActivityId, tStayId,
                final_boat, travelTitle, final_car, generalize, stick, String.valueOf(PreferenceUtil.getInt(UID)), lineOrThrow,
                PreferenceUtil.getString(TOKEN), stick_new, stick_low, stick_better, stick_throw, stick_rate,
                stick_return, stick_hot, stick_zeroC, goals_city, goals_pro, goals_city_code, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 微信支付
     *
     * @return
     */
    public Observable<AppPayEntity> apppay(String totalMoney, String payment,
                                           String body, String subjecy,
                                           String comboId, String comboType,
                                           String matchingPrice, String matchingMonth,
                                           String stickNumber, String stickPrice) {
        return ApiFactory.getFactory().BaiHangTongYeService().apppay(totalMoney, PreferenceUtil.getString(TOKEN),
                String.valueOf(PreferenceUtil.getInt(UID)), payment,
                body, subjecy,
                comboId, comboType,
                matchingPrice, matchingMonth,
                stickNumber, stickPrice)
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 支付宝支付
     *
     * @return
     */
    public Observable<AliPayEntity> apppayZhiFuBao(String totalMoney, String payment,
                                                   String body, String subjecy,
                                                   String comboId, String comboType,
                                                   String matchingPrice, String matchingMonth,
                                                   String stickNumber, String stickPrice) {
        return ApiFactory.getFactory().BaiHangTongYeService().apppayZhiFuBao(totalMoney, PreferenceUtil.getString(TOKEN),
                String.valueOf(PreferenceUtil.getInt(UID)), payment,
                body, subjecy,
                comboId, comboType,
                matchingPrice, matchingMonth,
                stickNumber, stickPrice)
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 跳转到通讯录页
     *
     * @return
     */
    public Observable<AddressBookEntity> addressBook(String uid, String token) {
        return ApiFactory.getFactory().BaiHangTongYeService().addressBook(uid, token)
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * Map转换类 (将Response类转换为需要的实体类)
     *
     * @param <T>
     */
    private class HttpResultFunc<T> implements Func1<Response<T>, T> {
        @Override
        public T call(Response<T> tResponse) {
            KyLog.object(tResponse);
//            if (tResponse.getStatus() != 200) {
            if (tResponse.getResultCode() != 0) {

                switch (tResponse.getResultCode()) {
                    case 110:
                        LoginActivity.jumpTo(HuXinApplication.mContext);
                        break;
                    case 1001:
//                        UpgradeMoneyActivity.jumpTo(WeiLaiYanApplication.mContext);
                        break;
                }
                throw new ApiException(tResponse.getResultMsg());
            }
            KyLog.object(tResponse.getData());
            return tResponse.getData();
        }
    }

    /**
     * Map转换类 (将Response类转换为需要的实体类)
     *
     * @param <T>
     */
    private class HttpPayResultFunc<T> implements Func1<Response<T>, T> {
        @Override
        public T call(Response<T> tResponse) {
            KyLog.object(tResponse);
            if (tResponse.getResultCode() != 0) {
//            if (tResponse.getStatus() != 1) {
                switch (tResponse.getResultCode()) {
                    case 1000:
//                        LoginActivity.jumpTo(WeiLaiYanApplication.mContext);
                        break;
                    case 1001:
//                        UpgradeMoneyActivity.jumpTo(WeiLaiYanApplication.mContext);
                        break;
                }
                throw new ApiException(tResponse.getResultMsg());
            }
//            if (!TextUtils.isEmpty(tResponse.getMsg())) {
////                String[] orderId = tResponse.getMsg().split(",");
////                PreferenceUtil.putString(Constanst.ORDRE_ID, orderId[orderId.length - 3]);
//            }
            KyLog.object(tResponse.getData());
            return tResponse.getData();
        }
    }
}
