package com.api.sns.cheese.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table t_account
 *
 * @mbg.generated do_not_delete_during_merge
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TAccount extends TAccountKey implements Serializable {
    /**
     * Database Column Remarks:
     *   ログインID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_account.login_id
     *
     * @mbg.generated
     */
    private String loginId;

    /**
     * Database Column Remarks:
     *   アカウント名
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_account.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     * Database Column Remarks:
     *   パスワード
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_account.password
     *
     * @mbg.generated
     */
    private String password;

    /**
     * Database Column Remarks:
     *   パスワード変更日時
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_account.password_change_date
     *
     * @mbg.generated
     */
    private Date passwordChangeDate;

    /**
     * Database Column Remarks:
     *   メールアドレス
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_account.mail
     *
     * @mbg.generated
     */
    private String mail;

    /**
     * Database Column Remarks:
     *   自己紹介
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_account.description
     *
     * @mbg.generated
     */
    private String description;

    /**
     * Database Column Remarks:
     *   場所
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_account.place
     *
     * @mbg.generated
     */
    private String place;

    /**
     * Database Column Remarks:
     *   ウェブサイト
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_account.url
     *
     * @mbg.generated
     */
    private String url;

    /**
     * Database Column Remarks:
     *   画像パス
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_account.img_url
     *
     * @mbg.generated
     */
    private String imgUrl;

    /**
     * Database Column Remarks:
     *   Twitterアカウント
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_account.twitter
     *
     * @mbg.generated
     */
    private String twitter;

    /**
     * Database Column Remarks:
     *   Facebookアカウント
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_account.facebook
     *
     * @mbg.generated
     */
    private String facebook;

    /**
     * Database Column Remarks:
     *   Instagramアカウント
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_account.instagram
     *
     * @mbg.generated
     */
    private String instagram;

    /**
     * Database Column Remarks:
     *   削除フラグ
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_account.deleted
     *
     * @mbg.generated
     */
    private String deleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_account
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_account.login_id
     *
     * @return the value of t_account.login_id
     *
     * @mbg.generated
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_account.login_id
     *
     * @param loginId the value for t_account.login_id
     *
     * @mbg.generated
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId == null ? null : loginId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_account.name
     *
     * @return the value of t_account.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_account.name
     *
     * @param name the value for t_account.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_account.password
     *
     * @return the value of t_account.password
     *
     * @mbg.generated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_account.password
     *
     * @param password the value for t_account.password
     *
     * @mbg.generated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_account.password_change_date
     *
     * @return the value of t_account.password_change_date
     *
     * @mbg.generated
     */
    public Date getPasswordChangeDate() {
        return passwordChangeDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_account.password_change_date
     *
     * @param passwordChangeDate the value for t_account.password_change_date
     *
     * @mbg.generated
     */
    public void setPasswordChangeDate(Date passwordChangeDate) {
        this.passwordChangeDate = passwordChangeDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_account.mail
     *
     * @return the value of t_account.mail
     *
     * @mbg.generated
     */
    public String getMail() {
        return mail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_account.mail
     *
     * @param mail the value for t_account.mail
     *
     * @mbg.generated
     */
    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_account.description
     *
     * @return the value of t_account.description
     *
     * @mbg.generated
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_account.description
     *
     * @param description the value for t_account.description
     *
     * @mbg.generated
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_account.place
     *
     * @return the value of t_account.place
     *
     * @mbg.generated
     */
    public String getPlace() {
        return place;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_account.place
     *
     * @param place the value for t_account.place
     *
     * @mbg.generated
     */
    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_account.url
     *
     * @return the value of t_account.url
     *
     * @mbg.generated
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_account.url
     *
     * @param url the value for t_account.url
     *
     * @mbg.generated
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_account.img_url
     *
     * @return the value of t_account.img_url
     *
     * @mbg.generated
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_account.img_url
     *
     * @param imgUrl the value for t_account.img_url
     *
     * @mbg.generated
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_account.twitter
     *
     * @return the value of t_account.twitter
     *
     * @mbg.generated
     */
    public String getTwitter() {
        return twitter;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_account.twitter
     *
     * @param twitter the value for t_account.twitter
     *
     * @mbg.generated
     */
    public void setTwitter(String twitter) {
        this.twitter = twitter == null ? null : twitter.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_account.facebook
     *
     * @return the value of t_account.facebook
     *
     * @mbg.generated
     */
    public String getFacebook() {
        return facebook;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_account.facebook
     *
     * @param facebook the value for t_account.facebook
     *
     * @mbg.generated
     */
    public void setFacebook(String facebook) {
        this.facebook = facebook == null ? null : facebook.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_account.instagram
     *
     * @return the value of t_account.instagram
     *
     * @mbg.generated
     */
    public String getInstagram() {
        return instagram;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_account.instagram
     *
     * @param instagram the value for t_account.instagram
     *
     * @mbg.generated
     */
    public void setInstagram(String instagram) {
        this.instagram = instagram == null ? null : instagram.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_account.deleted
     *
     * @return the value of t_account.deleted
     *
     * @mbg.generated
     */
    public String getDeleted() {
        return deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_account.deleted
     *
     * @param deleted the value for t_account.deleted
     *
     * @mbg.generated
     */
    public void setDeleted(String deleted) {
        this.deleted = deleted == null ? null : deleted.trim();
    }
}