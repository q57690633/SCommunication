package com.huxin.communication.http.service;


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
import com.huxin.communication.entity.MoreSimpleEntity;
import com.huxin.communication.entity.PersonProductEntity;
import com.huxin.communication.entity.PhoneSearchEntity;
import com.huxin.communication.entity.ProvinceEntity;
import com.huxin.communication.entity.RegisterEntity;
import com.huxin.communication.entity.RemoveCollectEntity;
import com.huxin.communication.entity.RentalScreeningEntity;
import com.huxin.communication.entity.SaleOfScreeningEntity;
import com.huxin.communication.entity.SaleOfScreeningNewEntity;
import com.huxin.communication.entity.SelectByLikeEntity;
import com.huxin.communication.entity.SelectFrameEntity;
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
import com.huxin.communication.http.Response;
import com.huxin.communication.http.ResponseUntil;
import com.lzy.imagepicker.bean.ImageItem;

import java.io.File;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import rx.Observable;

/**
 * Created by kyosky on 2016/11/21.
 */

public interface BaiHangTongYeService {


    /**
     * 注册
     *
     * @param registerType
     * @param phone
     * @param authCode
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("user/userRegister")
    Observable<Response<RegisterEntity>> Register(@Field("registerType") int registerType, @Field("phone") String phone,
                                                  @Field("authCode") String authCode, @Field("password") String password);

    /**
     * 发送验证码
     *
     * @param phone
     * @return
     */
    @FormUrlEncoded
    @POST("user/getAuthCode")
    Observable<ResponseUntil> sendMessage(@Field("phone") String phone);

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("user/userLogin")
    Observable<Response<LoginEntity>> logins(@Field("phone") String phone, @Field("password") String password);

    /**
     * 忘记密码
     */
    @FormUrlEncoded
    @POST("user/resetPassword")
    Observable<Response<ForgetPasswordEntity>> forgetPassword(@Field("phone") String phone, @Field("password") String passwor, @Field("authCode") String authCode);

    /**
     * 完善用户信息
     */
    @FormUrlEncoded
    @POST("user/addUserInformation")
    Observable<Response<AddUserInformationEntity>> addUserInformation(@Field("province") String province, @Field("city") String city,
                                                                      @Field("county") String county, @Field("companyName") String companyName,
                                                                      @Field("licenseCode") String licenseCode, @Field("position") String position,
                                                                      @Field("industryType") String industryType, @Field("invitationCode1") String invitationCode1,
                                                                      @Field("invitationCode2") String invitationCode2, @Field("cOrP") String cOrP, @Field("uid") String uid,
                                                                      @Field("token") String token, @Field("phone") String phone);

    /**
     * 更新用户信息
     */
    @FormUrlEncoded
    @POST("user/updateUserInformation")
    Observable<Response<UpdateUserInformationEntity>> updateUserInformation(@Field("username") String username, @Field("area") String area,
                                                                            @Field("storeName") String storeName, @Field("position") String position,
                                                                            @Field("industryType") String industryType, @Field("uid") String uid,
                                                                            @Field("token") String token, @Field("companyName") String companyName);

    /**
     * 更新注册电话
     */
    @FormUrlEncoded
    @POST("user/updateUserPhone")
    Observable<Response<UpdateUserPhoneEntity>> updateUserPhone(@Field("phone") String phone, @Field("authCode") String authCode,
                                                                @Field("secondPhone") String secondPhone, @Field("secondAuthCode") String secondAuthCode,
                                                                @Field("uid") String uid, @Field("token") String token);

    /**
     * 用户信息反馈
     */
    @FormUrlEncoded
    @POST("user/returnAdvice")
    Observable<Response> returnAdvice(@Field("advice") String advice, @Field("adviceType") int adviceType,
                                      @Field("uid") String uid, @Field("token") String token);

    /**
     * 头像上传
     */
    @FormUrlEncoded
    @POST("user/uploadHeadPhoto")
    Observable<Response> uploadHeadPhoto(@Field("uid") String uid, @Field("token") String token);

    /**
     * 出售筛选
     */
    @FormUrlEncoded
    @POST("houseProduct/SaleOfScreening")
    Observable<Response<SaleOfScreeningEntity>> SaleOfScreening(@Field("villageName") String villageName,
                                                                @Field("houseType") String houseType, @Field("minAcreage") String minAcreage,
                                                                @Field("maxAcreage") String maxAcreage, @Field("minPrice") String minPrice,
                                                                @Field("maxPrice") String maxPrice, @Field("orientation") String orientation,
                                                                @Field("permit") String permit, @Field("fitment") String fitment,
                                                                @Field("element") String element, @Field("floorAge") String floorAge,
                                                                @Field("purpose") String purpose, @Field("ownership") String ownership,
                                                                @Field("productType") String productType,
                                                                @Field("newOrOld") String newOrOld, @Field("token") String token,
                                                                @Field("city") String city, @Field("areaOne") String areaOne,
                                                                @Field("curPage") String curPage, @Field("houseHoldAppliances") String houseHoldAppliances);

    /**
     * 查看相似房源
     */
    @FormUrlEncoded
    @POST("houseProduct/getMoreSimple")
    Observable<Response<List<MoreSimpleEntity>>> getMoreSimple(@Field("id") String id, @Field("productType") String productType);

    /**
     * 出租筛选
     */
    @FormUrlEncoded
    @POST("houseProduct/rentalScreening")
    Observable<Response<RentalScreeningEntity>> rentalScreening(@Field("villageName") String villageName,
                                                                @Field("houseType") String houseType, @Field("minAcreage") String minAcreage,
                                                                @Field("maxAcreage") String maxAcreage, @Field("minPrice") String minPrice,
                                                                @Field("maxPrice") String maxPrice, @Field("orientation") String orientation,
                                                                @Field("permit") String permit, @Field("fitment") String fitment,
                                                                @Field("element") String element, @Field("floorAge") String floorAge,
                                                                @Field("purpose") String purpose, @Field("ownership") String ownership,
                                                                @Field("productType") String productType,
                                                                @Field("newOrOld") String newOrOld, @Field("token") String token,
                                                                @Field("city") String city, @Field("areaOne") String areaOne,
                                                                @Field("curPage") String curPage, @Field("houseHoldAppliances") String houseHoldAppliances);

    /**
     * 求购筛选
     */
    @FormUrlEncoded
    @POST("houseProduct/buyerScreening")
    Observable<Response<BuyerScreeningEntity>> buyerScreening(@Field("villageName") String villageName,
                                                              @Field("houseType") String houseType, @Field("minAcreage") String minAcreage,
                                                              @Field("maxAcreage") String maxAcreage, @Field("minPrice") String minPrice,
                                                              @Field("maxPrice") String maxPrice, @Field("orientation") String orientation,
                                                              @Field("permit") String permit, @Field("fitment") String fitment,
                                                              @Field("element") String element, @Field("floorAge") String floorAge,
                                                              @Field("purpose") String purpose, @Field("ownership") String ownership,
                                                              @Field("productType") String productType,
                                                              @Field("newOrOld") String newOrOld, @Field("token") String token,
                                                              @Field("city") String city, @Field("areaOne") String areaOne,
                                                              @Field("curPage") String curPage, @Field("houseHoldAppliances") String houseHoldAppliances);

    /**
     * 求租筛选
     */
    @FormUrlEncoded
    @POST("houseProduct/wantedScreening")
    Observable<Response<WantedScreeningEntity>> wantedScreening(@Field("villageName") String villageName,
                                                                @Field("houseType") String houseType, @Field("minAcreage") String minAcreage,
                                                                @Field("maxAcreage") String maxAcreage, @Field("minPrice") String minPrice,
                                                                @Field("maxPrice") String maxPrice, @Field("orientation") String orientation,
                                                                @Field("permit") String permit, @Field("fitment") String fitment,
                                                                @Field("element") String element, @Field("floorAge") String floorAge,
                                                                @Field("purpose") String purpose, @Field("ownership") String ownership,
                                                                @Field("productType") String productType,
                                                                @Field("newOrOld") String newOrOld, @Field("token") String token,
                                                                @Field("city") String city, @Field("areaOne") String areaOne,
                                                                @Field("curPage") String curPage, @Field("houseHoldAppliances") String houseHoldAppliances);

    /**
     * 出售、出租商品详情
     */
    @FormUrlEncoded
    @POST("houseProduct/getInformation")
    Observable<Response<List<InformationDetailEntity>>> getInformation(@Field("pid") String pid,
                                                                       @Field("productType") String productType, @Field("token") String token);


    /**
     * 求购、求租商品详情
     */
    @FormUrlEncoded
    @POST("houseProduct/getInformation")
    Observable<Response<InformationDetailEntity>> getQiuZuInformation(@Field("pid") String pid,
                                                                      @Field("productType") String productType, @Field("token") String token);

    /**
     * 收藏列表
     */
    @FormUrlEncoded
    @POST("houseProduct/getCollectProduct")
    Observable<Response<CollectEntity>> getCollectProduct(@Field("uid") String uid, @Field("villageName") String villageName,
                                                          @Field("minAcreage") String minAcreage, @Field("maxAcreage") String maxAcreage,
                                                          @Field("houseType") String houseType, @Field("minPrice") String minPrice,
                                                          @Field("maxPrice") String maxPrice, @Field("element") String element,
                                                          @Field("newOrOld") String newOrOld, @Field("orientation") String orientation,
                                                          @Field("houseHoldAppliances") String houseHoldAppliances, @Field("fitment") String fitment,
                                                          @Field("permit") String permit, @Field("purpose") String purpose,
                                                          @Field("ownership") String ownership, @Field("floorAge") String floorAge,
                                                          @Field("productType") String productType, @Field("token") String token, @Field("curPage") String curPage);

    /**
     * 移出收藏夹
     */
    @FormUrlEncoded
    @POST("houseProduct/removeCollectProduct")
    Observable<Response<RemoveCollectEntity>> removeCollectProduct(@Field("uid") String uid, @Field("pid") String pid,
                                                                   @Field("productType") String productType, @Field("token") String token);

    /**
     * 发布数据查询
     *
     * @param newOrOld    1:出售，2：出租，3：求购，4：求租
     * @param productType 排序规则
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("houseProduct/getPersonProduct")
    Observable<Response<PersonProductEntity>> getPersonProduct(@Field("uid") String uid, @Field("villageName") String villageName,
                                                               @Field("minAcreage") String minAcreage, @Field("maxAcreage") String maxAcreage,
                                                               @Field("houseType") String houseType, @Field("minPrice") String minPrice,
                                                               @Field("maxPrice") String maxPrice, @Field("element") String element,
                                                               @Field("newOrOld") String newOrOld, @Field("orientation") String orientation,
                                                               @Field("houseHoldAppliances") String houseHoldAppliances, @Field("fitment") String fitment,
                                                               @Field("permit") String permit, @Field("purpose") String purpose,
                                                               @Field("ownership") String ownership, @Field("floorAge") String floorAge,
                                                               @Field("productType") String productType, @Field("token") String token, @Field("curPage") String curPage);


    /**
     * 删除个人数据
     */
    @FormUrlEncoded
    @POST("houseProduct/deletePersonProduct")
    Observable<Response> deletePersonProduct(@Field("pid") String pid,
                                             @Field("productType") String productType, @Field("token") String token);


    /**
     * 置顶精选查询
     */
    @FormUrlEncoded
    @POST("houseProduct/selectStick")
    Observable<Response<TopSelectionEntity>> selectStick(@Field("villageName") String villageName,
                                                         @Field("houseType") String houseType, @Field("minAcreage") String minAcreage,
                                                         @Field("maxAcreage") String maxAcreage, @Field("minPrice") String minPrice,
                                                         @Field("maxPrice") String maxPrice, @Field("orientation") String orientation,
                                                         @Field("permit") String permit, @Field("fitment") String fitment,
                                                         @Field("element") String element, @Field("floorAge") String floorAge,
                                                         @Field("purpose") String purpose, @Field("ownership") String ownership,
                                                         @Field("productType") String productType,
                                                         @Field("newOrOld") String newOrOld, @Field("token") String token,
                                                         @Field("city") String city, @Field("areaOne") String areaOne,
                                                         @Field("curPage") String curPage, @Field("houseHoldAppliances") String houseHoldAppliances);

    /**
     * 个人数据库修改
     */
    @FormUrlEncoded
    @POST("public/updatePersonProduct")
    Observable<Response> updatePersonProduct(@Field("id") String id, @Field("productType") String productType,
                                             @Field("token") String token, @Field("acreage") String acreage,
                                             @Field("totalPrice") String totalPrice, @Field("villageName") String villageName,
                                             @Field("floorNumber") String floorNumber, @Field("totalFloorNumber") String totalFloorNumber,
                                             @Field("newOrOld") String newOrOld, @Field("loans") String loans,
                                             @Field("houseHoldAppliances") String houseHoldAppliances, @Field("keying") String keying,
                                             @Field("fitment") String fitment, @Field("orientation") String orientation,
                                             @Field("title") String title, @Field("permit") String permit,
                                             @Field("purpose") String purpose, @Field("stick") String stick,
                                             @Field("tabId") String tabId, @Field("files") String files,
                                             @Field("unlimitedEstate") String unlimitedEstate, @Field("minPrice") String minPrice,
                                             @Field("maxPrice") String maxPrice, @Field("minAcreage") String minAcreage,
                                             @Field("maxAcreage") String maxAcreage, @Field("uid") String uid);


    /**
     * 商品转发
     *
     * @param id
     * @param productType
     * @return
     */
    @FormUrlEncoded
    @POST("public/transmit")
    Observable<Response> matchingProduct(@Field("id") String id, @Field("productType") String productType);


    /**
     * 房客匹配
     *
     * @param uid
     * @param productType 1:匹配求购，2：匹配求租
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("houseProduct/matchingProduct")
    Observable<Response<MatchingProductEntity>> matchingProduct(@Field("uid") String uid,
                                                                @Field("productType") String productType, @Field("token") String token);


    /**
     * 邀请码展示
     *
     * @param uid
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("user/selectInvitation")
    Observable<ResponseUntil> selectInvitation(@Field("uid") int uid, @Field("token") String token);

    /**
     * 添加到收藏夹
     *
     * @param pid
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("houseProduct/addCollectTravel")
    Observable<Response> addCollectTravel(@Field("uid") int uid, @Field("pid") String pid, @Field("productType") int productType, @Field("token") String token);


    /**
     * 首页
     *
     * @param areaOne
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("houseProduct/selectHome")
    Observable<Response<HomeEntity>> getHome(@Field("city") String city, @Field("areaOne") String areaOne, @Field("token") String token);


    /**
     * 房产标签查询
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("houseProduct/selectTab")
    Observable<Response<SelectTabEntity>> selectTab(@Field("productType") String productType, @Field("uid") String uid, @Field("token") String token);

    /**
     * 添加出售信息
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("public/addSaleProduct")
    Observable<Response<SaleOfScreeningNewEntity>> addSaleProduct(@Field("villageName") String villageName, @Field("acreage") String acreage,
                                                                  @Field("houseType") String houseType, @Field("totalPrice") String totalPrice,
                                                                  @Field("floorNumber") String floorNumber, @Field("totalFloorNumber") String totalFloorNumber,
                                                                  @Field("newOrOld") String newOrOld, @Field("loans") String loans,
                                                                  @Field("keying") String keying, @Field("houseHoldAppliances") String houseHoldAppliances,
                                                                  @Field("fitment") String fitment, @Field("permit") String permit,
                                                                  @Field("orientation") String orientation, @Field("purpose") String purpose,
                                                                  @Field("title") String title, @Field("uid") String uid,
                                                                  @Field("stick") String stick, @Field("exclusive") String exclusive,
                                                                  @Field("houseNumber") String houseNumber, @Field("pdu") String pdu,
                                                                  @Field("floorSize") String floorSize, @Field("tabId") String tabId,
                                                                  @Field("token") String token);

    /**
     * 添加出租信息
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("public/addRentProduct")
    Observable<Response<RentalScreeningEntity>> addRentProduct(@Field("villageName") String villageName, @Field("acreage") String acreage,
                                                               @Field("houseType") String houseType, @Field("price") String price,
                                                               @Field("floorNumber") String floorNumber, @Field("totalFloorNumber") String totalFloorNumber,
                                                               @Field("fitment") String fitment, @Field("keying") String keying,
                                                               @Field("paymentType") String paymentType,
                                                               @Field("title") String title, @Field("uid") String uid,
                                                               @Field("stick") String stick, @Field("tabId") String tabId,
                                                               @Field("exclusive") String exclusive, @Field("purpose") String purpose,
                                                               @Field("houseHoldAppliances") String houseHoldAppliances, @Field("orientation") String orientation,
                                                               @Field("houseNumber") String houseNumber, @Field("floorSize") String floorSize,
                                                               @Field("pdu") String pdu, @Field("token") String token);

    /**
     * 添加求购信息
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("public/addBuyerProduct")
    Observable<Response<BuyerScreeningEntity>> addBuyerProduct(@Field("villageName") String villageName, @Field("unlimitedEstate") String unlimitedEstate,
                                                               @Field("minPrice") String minPrice, @Field("maxPrice") String totalPrice,
                                                               @Field("minAcreage") String newOrOld, @Field("maxAcreage") String maxAcreage,
                                                               @Field("houseType") String houseType, @Field("floorAge") String floorAge,
                                                               @Field("permit") String permit, @Field("remark") String remark,
                                                               @Field("uid") String uid, @Field("stick") String stick,
                                                               @Field("tabId") String tabId, @Field("token") String token);

    /**
     * 添加求租信息
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("public/addWantedProduct")
    Observable<Response<WantedScreeningEntity>> addWantedProduct(@Field("villageName") String villageName, @Field("unlimitedEstate") String unlimitedEstate,
                                                                 @Field("minPrice") String minPrice, @Field("maxPrice") String totalPrice,
                                                                 @Field("minAcreage") String newOrOld, @Field("maxAcreage") String maxAcreage,
                                                                 @Field("houseType") String houseType, @Field("fitment") String fitment,
                                                                 @Field("paymentType") String paymentType, @Field("householdAppliances") String householdAppliances,
                                                                 @Field("remark") String remark,
                                                                 @Field("uid") String uid, @Field("stick") String stick,
                                                                 @Field("tabId") String tabId, @Field("token") String token);


    /**
     * 省
     */
    @FormUrlEncoded
    @POST("travel/getProvince")
    Observable<Response<List<ProvinceEntity>>> getProvinces(@Field("token") String token);

    /**
     * 市
     */
    @FormUrlEncoded
    @POST("travel/getInlandCity")
    Observable<Response<List<InlandCityEntity>>> getInlandCity(@Field("token") String token, @Field("province_code") String province_code);


    /**
     * 区
     */
    @FormUrlEncoded
    @POST("travel/getCounty")
    Observable<Response<List<CountyEntity>>> getCountys();


    /**
     * 周边游和国内游筛选和搜索
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("travel/gettingAroundTravel")
    Observable<Response<AroundTravelEntity>> gettingAroundTravel(@Field("depart_code") String depart_code, @Field("goalsId") String goalsId,
                                                                 @Field("sort_type") String sort_type, @Field("tOtherId") String tOtherId,
                                                                 @Field("tActivityId") String tActivityId, @Field("tStayId") String tStayId,
                                                                 @Field("tAddressId") String tAddressId, @Field("tTrafficId") String tTrafficId,
                                                                 @Field("tConsumeId") String tConsumeId, @Field("minPri_maxPri") String minPri_maxPri,
                                                                 @Field("numberDays") String numberDays,
                                                                 @Field("token") String token, @Field("keyWord") String keyWord,
                                                                 @Field("curPage") String curPage,
                                                                 @Field("minDay") String minDay, @Field("maxDay") String maxDay, @Field("uid") String uid,
                                                                 @Field("travel_kind") String travel_kind, @Field("lineOrThrow") String lineOrThrow);

    /**
     * 国外游筛选和搜索
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("travel/gettingForeignTravel")
    Observable<Response<ForeignTravelEntity>> gettingForeignTravel(@Field("depart_name") String depart_name, @Field("min_days") String min_days,
                                                                   @Field("max_days") String max_days, @Field("spot_name") String spot_name,
                                                                   @Field("goals_name") String goals_name, @Field("t_activity_id") String t_activity_id,
                                                                   @Field("t_stay_id") String t_stay_id, @Field("t_other_id") String t_other_id,
                                                                   @Field("t_address_id") String t_address_id, @Field("t_traffic_id") String t_traffic_id,
                                                                   @Field("t_overseas_id") String t_overseas_id,
                                                                   @Field("t_consume_id") String t_consume_id, @Field("sort_type") String sort_type,
                                                                   @Field("minPri_maxPri") String minPri_maxPri, @Field("number_days") String number_days,
                                                                   @Field("token") String token, @Field("keyWord") String keyWord,
                                                                   @Field("curPage") String curPage, @Field("uid") String uid, @Field("line_or_throw") String line_or_throw);

    /**
     * 票务筛选
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("travel/getTicketInfo")
    Observable<Response<TicketInfoEntity>> getTicketInfo(@Field("ticket_type") String ticket_type, @Field("ticket_city_name") String ticket_city_name,
                                                         @Field("minPri_maxPri") String minPri_maxPri, @Field("ticket_theme_id") String ticket_theme_id,
                                                         @Field("ticket_activity_id") String ticket_activity_id, @Field("ticket_other_id") String ticket_other_id,
                                                         @Field("sort_type") String sort_type, @Field("token") String token,
                                                         @Field("keyWord") String keyWord,
                                                         @Field("curPage") String curPage, @Field("uid") String uid);

    /**
     * 踩线或甩位-国外
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("travel/gettingForeignTravel")
    Observable<Response<CaixianForeignTravelEntity>> gettingCaiXianForeignTravel(@Field("line_or_throw") String line_or_throw, @Field("number_days") String number_days,
                                                                                 @Field("token") String token, @Field("depart_code") String depart_code,
                                                                                 @Field("min_days") String min_days, @Field("max_days") String max_days,
                                                                                 @Field("spot_name") String spot_name, @Field("goals_name") String goals_name,
                                                                                 @Field("t_activity_id") String t_activity_id,
                                                                                 @Field("t_stay_id") String t_stay_id, @Field("t_other_id") String t_other_id,
                                                                                 @Field("t_address_id") String t_address_id, @Field("t_traffic_id") String t_traffic_id,
                                                                                 @Field("t_overseas_id") String t_overseas_id, @Field("minPri_maxPri") String minPri_maxPri,
                                                                                 @Field("t_consume_id") String t_consume_id, @Field("sort_type") String sort_type);


    /**
     * 首页
     *
     * @param city_name
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("travel/selectHome")
    Observable<Response<HomeTravelEntity>> getTravelHome(@Field("city_name") String city_name, @Field("token") String token);


    /**
     * 发布周边游和国内游
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("travel/issueAroundRoute")
    Observable<Response<AroundTravelEntity>> issueAroundRoute(@Field("depart_code") String depart_code, @Field("depart_pro_code") String depart_pro_code,
                                                              @Field("goalsId") String goalsId, @Field("spotName") String spotName,
                                                              @Field("numberDays") String numberDays, @Field("totalPrice") String totalPrice,
                                                              @Field("finalPrice") String finalPrice, @Field("returnPrice") String returnPrice,
                                                              @Field("pickupPrice") String pickupPrice,
                                                              @Field("totalPriceChild") String totalPriceChild, @Field("finalPriceChild") String finalPriceChild,
                                                              @Field("returnPriceChild") String returnPriceChild, @Field("tAddressId") String tAddressId,
                                                              @Field("tTrafficId") String tTrafficId, @Field("tConsumeId") String tConsumeId,
                                                              @Field("tActivityId") String tActivityId, @Field("tStayId") String tStayId,
                                                              @Field("tOtherId") String tOtherId, @Field("travelTitle") String travelTitle,
                                                              @Field("generalize") String generalize, @Field("stick") String stick,
                                                              @Field("uid") String uid, @Field("lineOrThrow") String lineOrThrow,
                                                              @Field("token") String token, @Field("files") String files,
                                                              @Field("stick_new") String stick_new, @Field("stick_low") String stick_low,
                                                              @Field("stick_better") String stick_better, @Field("stick_throw") String stick_throw,
                                                              @Field("stick_rate") String stick_rate, @Field("stick_return") String stick_return,
                                                              @Field("stick_hot") String stick_hot, @Field("stick_zeroC") String stick_zeroC,
                                                              @Field("goals_city") String goals_city, @Field("goals_pro") String goals_pro,
                                                              @Field("goals_city_code") String goals_city_code, @Field("depart_name") String depart_name,
                                                              @Field("travel_kind") String travel_kind);

    /**
     * 发布国外游
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("travel/issueForeignRoute")
    Observable<Response<ForeignTravelEntity>> issueForeignRoute(@Field("depart_name") String depart_name, @Field("depart_pro_name") String depart_pro_name,
                                                                @Field("goals_nat_name") String goals_nat_name, @Field("goals_name") String goals_name,
                                                                @Field("spot_name") String spot_name, @Field("stick") String stick,
                                                                @Field("uid") String uid, @Field("line_or_throw") String line_or_throw,
                                                                @Field("token") String token,
                                                                @Field("number_days") String number_days, @Field("total_price") String total_price,
                                                                @Field("final_price") String final_price, @Field("return_price") String return_price,
                                                                @Field("pickup_price") String pickup_price, @Field("total_price_child") String total_price_child,
                                                                @Field("final_price_child") String final_price_child, @Field("return_price_child") String return_price_child,
                                                                @Field("t_address_id") String t_address_id, @Field("t_traffic_id") String t_traffic_id,
                                                                @Field("t_consume_id") String t_consume_id, @Field("t_activity_id") String t_activity_id,
                                                                @Field("t_stay_id") String t_stay_id, @Field("t_other_id") String t_other_id,
                                                                @Field("travel_title") String travel_title, @Field("generalize") String generalize,
                                                                @Field("files") String files, @Field("stick_new") String stick_new,
                                                                @Field("stick_low") String stick_low, @Field("stick_better") String stick_better,
                                                                @Field("stick_throw") String stick_throw, @Field("stick_rate") String stick_rate,
                                                                @Field("stick_return") String stick_return, @Field("stick_hot") String stick_hot,
                                                                @Field("stick_zeroC") String stick_zeroC);

    /**
     * 发布票务
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("travel/issueForeignRoute")
    Observable<Response<TicketInfoEntity>> issueTicketForeignRoute(@Field("ticket_pro_name") String ticket_pro_name, @Field("ticket_city_name") String ticket_city_name,
                                                                   @Field("ticket_name") String ticket_name, @Field("ticket_addr") String ticket_addr,
                                                                   @Field("ticket_type") String ticket_type, @Field("open_time") String open_time,
                                                                   @Field("original_price") String original_price, @Field("final_price") String final_price,
                                                                   @Field("original_price_child") String original_price_child,
                                                                   @Field("final_price_child") String final_price_child, @Field("original_price_evening") String original_price_evening,
                                                                   @Field("final_price_evening") String final_price_evening, @Field("original_price_parent_child") String original_price_parent_child,
                                                                   @Field("final_price_parent_child") String final_price_parent_child, @Field("original_price_family") String original_price_family,
                                                                   @Field("final_price_family") String final_price_family, @Field("original_boat") String original_boat,
                                                                   @Field("final_boat") String final_boat, @Field("original_car") String original_car,
                                                                   @Field("final_car") String final_car, @Field("ticket_theme_id") String ticket_theme_id,
                                                                   @Field("ticket_activity_id") String ticket_activity_id, @Field("ticket_other_id") String ticket_other_id,
                                                                   @Field("uid") String uid, @Field("stick") String stick,
                                                                   @Field("line_or_throw") String line_or_throw, @Field("files") String files,
                                                                   @Field("token") String token, @Field("stick_new") String stick_new,
                                                                   @Field("stick_low") String stick_low, @Field("stick_better") String stick_better,
                                                                   @Field("stick_throw") String stick_throw, @Field("stick_rate") String stick_rate,
                                                                   @Field("stick_return") String stick_return, @Field("stick_hot") String stick_hot, @Field("stick_zeroC") String stick_zeroC,
                                                                   @Field("generalize") String generalize, @Field("original_price_total") String original_price_total,
                                                                   @Field("final_price_total") String final_price_total, @Field("ticket_pro_code") String ticket_pro_code);


    /**
     * 查询标签
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("travel/selectTab")
    Observable<Response<TabTravelNameEntity>> selectTab(@Field("tag_kind") String tag_kind, @Field("token") String token);


    /**
     * 根据城市查目的地景点
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("travel/getInlandSpot")
    Observable<Response<List<InlandSpotEntity>>> getInlandSpot(@Field("city_code") String city_code, @Field("token") String token);

    /**
     * 国外游-获得所有国家信息接口
     */
    @FormUrlEncoded
    @POST("travel/getForeignNation")
    Observable<Response<List<ForeignNationEntity>>> getForeignNation(@Field("token") String token);

    /**
     * 国外游-获得所有国外城市信息接口
     */
    @FormUrlEncoded
    @POST("travel/getForeignCity")
    Observable<Response<List<ForeignCityEntity>>> getForeignCity(@Field("token") String token, @Field("nation_name") String nation_name);

    /**
     * 国外游-获得所有景点信息接口(根据城市名)
     */
    @FormUrlEncoded
    @POST("travel/getForeignSpot")
    Observable<Response<List<ForeignSpotEntity>>> getForeignSpot(@Field("token") String token, @Field("city_name") String city_name);

    /**
     * 踩线或甩位查询-国内和周边
     */
    @FormUrlEncoded
    @POST("travel/gettingAroundTravel")
    Observable<Response<ForeignTraveAroundEntity>> gettingCaiXianAroundTravel(@Field("line_or_throw") String line_or_throw,
                                                                              @Field("token") String token, @Field("maxDay") String maxDay,
                                                                              @Field("minPri_maxPri") String minPri_maxPri, @Field("numberDays") String numberDays,
                                                                              @Field("minDay") String minDay, @Field("depart_code") String depart_code,
                                                                              @Field("goalsId") String goalsId,
                                                                              @Field("sort_type") String sort_type, @Field("tOtherId") String tOtherId,
                                                                              @Field("tActivityId") String tActivityId, @Field("tStayId") String tStayId,
                                                                              @Field("tAddressId") String tAddressId, @Field("tTrafficId") String tTrafficId,
                                                                              @Field("tConsumeId") String tConsumeId);

    /**
     * 个人收藏查询-国内和周边
     */
    @FormUrlEncoded
    @POST("travel/getCollectAround")
    Observable<Response<AroundTravelEntity>> getCollectAround(@Field("depart_code") String depart_code, @Field("goalsId") String goalsId,
                                                              @Field("sort_type") String sort_type, @Field("tOtherId") String tOtherId,
                                                              @Field("tActivityId") String tActivityId, @Field("tStayId") String tStayId,
                                                              @Field("tAddressId") String tAddressId, @Field("tTrafficId") String tTrafficId,
                                                              @Field("tConsumeId") String tConsumeId, @Field("minPri_maxPri") String minPri_maxPri,
                                                              @Field("numberDays") String numberDays,
                                                              @Field("token") String token, @Field("keyWord") String keyWord,
                                                              @Field("curPage") String curPage,
                                                              @Field("minDay") String minDay, @Field("maxDay") String maxDay, @Field("uid") String uid,
                                                              @Field("travel_kind") String travel_kind);

    /**
     * 个人收藏查询-境外
     */
    @FormUrlEncoded
    @POST("travel/getCollectForeign")
    Observable<Response<ForeignTravelEntity>> getCollectForeign(@Field("depart_name") String depart_name, @Field("min_days") String min_days,
                                                                @Field("max_days") String max_days, @Field("spot_name") String spot_name,
                                                                @Field("goals_name") String goals_name, @Field("t_activity_id") String t_activity_id,
                                                                @Field("t_stay_id") String t_stay_id, @Field("t_other_id") String t_other_id,
                                                                @Field("t_address_id") String t_address_id, @Field("t_traffic_id") String t_traffic_id,
                                                                @Field("t_overseas_id") String t_overseas_id,
                                                                @Field("t_consume_id") String t_consume_id, @Field("sort_type") String sort_type,
                                                                @Field("minPri_maxPri") String minPri_maxPri, @Field("number_days") String number_days,
                                                                @Field("token") String token, @Field("keyWord") String keyWord,
                                                                @Field("curPage") String curPage, @Field("uid") String uid);

    /**
     * 个人收藏查询-票务
     */
    @FormUrlEncoded
    @POST("travel/getCollectTicket")
    Observable<Response<TicketInfoEntity>> getCollectTicket(@Field("ticket_type") String ticket_type, @Field("ticket_city_name") String ticket_city_name,
                                                            @Field("minPri_maxPri") String minPri_maxPri, @Field("ticket_theme_id") String ticket_theme_id,
                                                            @Field("ticket_activity_id") String ticket_activity_id, @Field("ticket_other_id") String ticket_other_id,
                                                            @Field("sort_type") String sort_type, @Field("token") String token,
                                                            @Field("keyWord") String keyWord,
                                                            @Field("curPage") String curPage, @Field("uid") String uid);


    /**
     * 添加到收藏夹
     *
     * @param ids
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("travel/addCollectTravel")
    Observable<ResponseUntil> addTravelCollect(@Field("uid") int uid, @Field("ids") String ids, @Field("travelType") int travelType, @Field("token") String token);

    /**
     * 周边游和国内游置顶精选
     */
    @FormUrlEncoded
    @POST("travel/selectAroundStick")
    Observable<Response<AroundStickEntity>> selectAroundStick(@Field("depart_code") String depart_code,
                                                              @Field("goalsId") String goalsId, @Field("sort_type") String sort_type,
                                                              @Field("tOtherId") String tOtherId, @Field("tActivityId") String tActivityId,
                                                              @Field("tStayId") String tStayId, @Field("tAddressId") String tAddressId,
                                                              @Field("tTrafficId") String tTrafficId,
                                                              @Field("tConsumeId") String tConsumeId, @Field("minPri_maxPri") String minPri_maxPri,
                                                              @Field("numberDays") String numberDays, @Field("token") String token,
                                                              @Field("keyWord") String keyWord, @Field("curPage") String curPage,
                                                              @Field("uid") String uid, @Field("minDay") String minDay,
                                                              @Field("maxDay") String maxDay, @Field("stick") String stick);

    /**
     * 票务置顶精选
     */
    @FormUrlEncoded
    @POST("travel/selectTicketStick")
    Observable<Response<TicketStickEntity>> selectTicketStick(@Field("ticket_type") String ticket_type,
                                                              @Field("ticket_city_name") String ticket_city_name, @Field("minPri_maxPri") String minPri_maxPri,
                                                              @Field("ticket_theme_id") String ticket_theme_id, @Field("ticket_activity_id") String ticket_activity_id,
                                                              @Field("ticket_other_id") String ticket_other_id, @Field("sort_type") String sort_type,
                                                              @Field("token") String token,
                                                              @Field("keyWord") String keyWord, @Field("uid") String uid,
                                                              @Field("curPage") String curPage, @Field("stick") String stick);

    /**
     * 国外游置顶精选
     */
    @FormUrlEncoded
    @POST("travel/selectForeignStick")
    Observable<Response<ForeignStickEntity>> selectForeignStick(@Field("depart_name") String depart_name, @Field("min_days") String min_days,
                                                                @Field("max_days") String max_days, @Field("spot_name") String spot_name,
                                                                @Field("goals_name") String goals_name, @Field("t_activity_id") String t_activity_id,
                                                                @Field("t_stay_id") String t_stay_id, @Field("t_other_id") String t_other_id,
                                                                @Field("t_address_id") String t_address_id, @Field("t_traffic_id") String t_traffic_id,
                                                                @Field("t_overseas_id") String t_overseas_id,
                                                                @Field("t_consume_id") String t_consume_id, @Field("sort_type") String sort_type,
                                                                @Field("minPri_maxPri") String minPri_maxPri, @Field("number_days") String number_days,
                                                                @Field("token") String token, @Field("keyWord") String keyWord,
                                                                @Field("curPage") String curPage, @Field("uid") String uid, @Field("stick") String stick);

    /**
     * 周边游个人数据库查询
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("travel/updatePersonageTravel")
    Observable<Response<AroundTravelEntity>> updatePersonageTravel(@Field("depart_code") String depart_code, @Field("depart_pro_code") String depart_pro_code,
                                                                   @Field("goalsId") String goalsId, @Field("spotName") String spotName,
                                                                   @Field("numberDays") String numberDays, @Field("totalPrice") String totalPrice,
                                                                   @Field("finalPrice") String finalPrice, @Field("returnPrice") String returnPrice,
                                                                   @Field("pickupPrice") String pickupPrice,
                                                                   @Field("totalPriceChild") String totalPriceChild, @Field("finalPriceChild") String finalPriceChild,
                                                                   @Field("returnPriceChild") String returnPriceChild, @Field("tAddressId") String tAddressId,
                                                                   @Field("tTrafficId") String tTrafficId, @Field("tConsumeId") String tConsumeId,
                                                                   @Field("tActivityId") String tActivityId, @Field("tStayId") String tStayId,

                                                                   @Field("final_boat") String final_boat, @Field("travelTitle") String travelTitle,
                                                                   @Field("generalize") String final_car, @Field("ticket_theme_id") String generalize,

                                                                   @Field("stick") String stick,
                                                                   @Field("uid") String uid, @Field("lineOrThrow") String lineOrThrow,
                                                                   @Field("token") String token, @Field("stick_new") String stick_new,
                                                                   @Field("stick_low") String stick_low, @Field("stick_better") String stick_better,
                                                                   @Field("stick_throw") String stick_throw, @Field("stick_rate") String stick_rate,
                                                                   @Field("stick_return") String stick_return, @Field("stick_hot") String stick_hot, @Field("stick_zeroC") String stick_zeroC,
                                                                   @Field("goals_city") String goals_city, @Field("goals_pro") String goals_pro,
                                                                   @Field("goals_city_code") String goals_city_code, @Field("id") String id);


    /**
     * 国外游个人数据库查询
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("travel/updatePersonageForiegn")
    Observable<Response<ForeignTravelEntity>> updatePersonageForiegn(@Field("depart_name") String depart_name, @Field("min_days") String min_days,
                                                                     @Field("max_days") String max_days, @Field("spot_name") String spot_name,
                                                                     @Field("goals_name") String goals_name, @Field("t_activity_id") String t_activity_id,
                                                                     @Field("t_stay_id") String t_stay_id, @Field("t_other_id") String t_other_id,
                                                                     @Field("t_address_id") String t_address_id, @Field("t_traffic_id") String t_traffic_id,
                                                                     @Field("t_overseas_id") String t_overseas_id,
                                                                     @Field("t_consume_id") String t_consume_id, @Field("sort_type") String sort_type,
                                                                     @Field("minPri_maxPri") String minPri_maxPri, @Field("number_days") String number_days,
                                                                     @Field("token") String token, @Field("keyWord") String keyWord,
                                                                     @Field("curPage") String curPage, @Field("uid") String uid, @Field("line_or_throw") String line_or_throw);


    /**
     * 票务筛选
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("travel/updatePersonageTicket")
    Observable<Response<TicketInfoEntity>> updatePersonageTicket(@Field("ticket_type") String ticket_type, @Field("ticket_city_name") String ticket_city_name,
                                                                 @Field("minPri_maxPri") String minPri_maxPri, @Field("ticket_theme_id") String ticket_theme_id,
                                                                 @Field("ticket_activity_id") String ticket_activity_id, @Field("ticket_other_id") String ticket_other_id,
                                                                 @Field("sort_type") String sort_type, @Field("token") String token,
                                                                 @Field("keyWord") String keyWord,
                                                                 @Field("curPage") String curPage, @Field("uid") String uid);

    /**
     * 跳转到vip
     */
    @FormUrlEncoded
    @POST("user/toVip")
    Observable<Response<ToVipEntity>> toVip(@Field("token") String token, @Field("productType") String productType);

    /**
     * 确认支付
     */
    @FormUrlEncoded
    @POST("weixin/apppay")
    Observable<Response<AppPayEntity>> apppay(@Field("totalMoney") String totalMoney, @Field("token") String token,
                                              @Field("uid") String uid, @Field("payment") String payment,
                                              @Field("body") String body, @Field("subjecy") String subjecy,
                                              @Field("comboId") String comboId, @Field("comboType") String comboType,
                                              @Field("matchingPrice") String matchingPrice, @Field("matchingMonth") String matchingMonth,
                                              @Field("stickNumber") String stickNumber, @Field("stickPrice") String stickPrice);

    /**
     * 确认支付
     */
    @FormUrlEncoded
    @POST("weixin/apppay")
    Observable<Response<AliPayEntity>> apppayZhiFuBao(@Field("totalMoney") String totalMoney, @Field("token") String token,
                                                      @Field("uid") String uid, @Field("payment") String payment,
                                                      @Field("body") String body, @Field("subjecy") String subjecy,
                                                      @Field("comboId") String comboId, @Field("comboType") String comboType,
                                                      @Field("matchingPrice") String matchingPrice, @Field("matchingMonth") String matchingMonth,
                                                      @Field("stickNumber") String stickNumber, @Field("stickPrice") String stickPrice);

    /**
     * 小区搜索
     */
    @FormUrlEncoded
    @POST("houseProduct/selectPlot")
    Observable<Response<List<SelectPlotEntity>>> selectPlot(@Field("areaSecondId") String areaSecondId, @Field("areaId") String areaId,
                                                            @Field("token") String token);

    /**
     * 灏忓尯妯＄硦鏌ヨ
     */
    @FormUrlEncoded
    @POST("public/selectByLike")
    Observable<Response<List<SelectByLikeEntity>>> selectByLike(@Field("villageName") String villageName, @Field("token") String token,
                                                                @Field("uid") String uid);


    /**
     * 娣诲姞灏忓尯
     */
    @FormUrlEncoded
    @POST("public/addPlot")
    Observable<Response> addPlot(@Field("city") String city, @Field("areaOne") String areaOne,
                                 @Field("areaTwo") String areaTwo, @Field("villageName") String villageName,
                                 @Field("token") String token);


    /**
     * 下属区域筛选
     */
    @FormUrlEncoded
    @POST("houseProduct/areaTwoScreen")
    Observable<Response<List<AreaTwoScreenEntity>>> areaTwoScreen(@Field("token") String token, @Field("areaId") String areaId);

    /**
     * 城区筛选
     */
    @FormUrlEncoded
    @POST("houseProduct/areaOneScreen")
    Observable<Response<List<AreaOneScreenEntity>>> areaOneScreen(@Field("token") String token, @Field("city") String city);
    //user/toAddressBook

    /**
     * 跳转到通讯录页
     */
    @FormUrlEncoded
    @POST("user/toAddressBook")
    Observable<Response<AddressBookEntity>> addressBook(@Field("uid") String uid, @Field("token") String token);

    /**
     * 添加腾讯云好友关系
     */
    @FormUrlEncoded
    @POST("user/addTencentFriend")
    Observable<Response> addTencentFriend(@Field("uid") String uid, @Field("friendId") String friendId, @Field("token") String token);

    /**
     * 添加腾讯云好友关系
     */
    @FormUrlEncoded
    @POST("user/addFlockMember")
    Observable<Response> addFlockMember(@Field("uid") String uid, @Field("groupId") String groupId, @Field("token") String token);

    /**
     * 通讯录搜索
     */
    @FormUrlEncoded
    @POST("user/selectAddressBook")
    Observable<Response<PhoneSearchEntity>> selectAddressBook(@Field("username") String username, @Field("token") String token);

    /**
     * 添加/删除星标好友   添加时候传1，删除时候传0
     */
    @FormUrlEncoded
    @POST("user/addStarFriend")
    Observable<Response> addStarFriend(@Field("uid") String uid, @Field("friendId") String friendId,
                                       @Field("type") String type, @Field("token") String token);


    /**
     * 房产搜索接口
     */
    @FormUrlEncoded
    @POST("houseProduct/selectFrame")
    Observable<Response<List<SelectFrameEntity>>> selectFrame(@Field("productType") String productType, @Field("newOrOld") String newOrOld,
                                                        @Field("condition") String condition, @Field("token") String token,
                                                        @Field("uid") String uid, @Field("stick") String stick,
                                                        @Field("collectType") String collectType);

}
