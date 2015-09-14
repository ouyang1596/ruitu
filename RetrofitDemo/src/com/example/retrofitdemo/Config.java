package com.example.retrofitdemo;


public class Config {
	public static final boolean DEBUG = true;
	public static final String IMAGE_HOST = "http://img.wlyeah.com";
	// public static final String HOST = "http://meeting.deshang365.com";
	public static final String HOST = "http://api2.wlyeah.com";
	// public static final String HOST = "http://10.13.1.3:8703";
	// public static final String HOST = "http://10.13.1.3:8704";
	// public static final String HOST = "http://10.13.1.3:8199";
	// public static final String HOST = "http://10.13.1.3:4444";
	public static final int HTTP_CONNECT_TIMEOUT = 10 * 1000;
	public static final int HTTP_READ_TIMEOUT = 10 * 1000;
	public static final int RESPONSE_CACHE_SIZE = 1 * 1024 * 1024;
	public static final String RESPONSE_CACHE = "http_cache";
	static final String API_LOGIN = "/api/user_des2/login";
	static final String API_CREATE_GROUP = "/api/group_des2/groupadd";
	static final String API_JOIN_GROUP = "/api/group_des2/groupuseradd";
	static final String API_GROUPLIST = "/api/group_des2/mix_grouplist";
	static final String API_GROUPINFO = "/api/group_des2/groupinfo_byidcard";
	static final String API_ABSENT_RESULT = "/api/meeting_des2/paging_absent_results_v1";
	public static final String API_SIGNLIST = "/api/meeting_des2/meetinglist_v1";
	static final String API_GROUPMEMBERS = "/api/group_des2/groupmemberlist";
	public static final String API_GROUPINFO_BY_MOB = "/api/group_des2/groupinfo_bymobcode";
	public static final String API_GROUPINFO_BY_ID = "/api/group_des2/groupinfo_byid?p={\"id\":%s}";
	public static final String API_GET_NEAR_SIGNGROUPS = "/api/group_des2/near_groups";
	public static final String API_GET_GROUPMEMBERS_BY_HXID = "/api/group_des2/groupdetail_bymobcode";
	public static final String API_UPLOAD_IMAGE = "/api/avatar_des2/avatar/";
	public static final String API_SET_PASSWORD = "/api/user_des2/change_pwd";
	public static final String API_REGISTER = "/api/user_des2/register";
	public static final String API_GROUP_EXIT = "/api/group_des2/groupexit";
	public static final String API_GROUP_DISMISS = "/api/group_des2/groupdismiss";
	public static final String API_PERSONAL_SIGN_RECORD = "/api/meeting_des2/historymeetinglist_personal?p={\"group_id\":%s}";
	public static final String API_GROUP_SIGN_RECORD = "/api/meeting_des2/historymeetinglist_group?p={\"group_id\":%s}";
	public static final String API_CREATE_SIGN = "/api/meeting_des2/meetingadd_v1";
	public static final String API_STOP_SIGN = "/api/meeting_des2/end_meeting";
	public static final String API_CHANGE_SIGN_STATE = "/api/meeting_des2/change_state_byuid";
	public static final String API_SINGLE_RESULT = "/api/meeting_des2/export_single_meeting_results";
	public static final String API_ALL_SIGN_RESULT = "/api/meeting_des2/export_multiple_meeting_results";
	public static final String API_GET_USER_NAME_BY_HXID = "/api/group_des2/ginfo_bymobcode?p={\"mob_code\":\"%s\"}";
	public static final String API_SET_NEWPWD = "/api/user_des2/fchange_pwd";
	public static final String API_SET_LATLNG = "/api/meeting_des2/meetingadd";
	public static final String API_USER_SIGN = "/api/meeting_des2/sign";
	public static final String API_LOGOUT = "/api/user_des2/logout";
	public static final String API_USERINFO = "/api/user_des2/getstudentinfo";
	// public static final String API_UPDATE =
	// "http://resource.deshang365.com/ugc/meeting_update.json?userId=%s&userName=%s&schoolId=%s&deviceId=%s";
	public static final String API_UPDATE = "/api/user_des2/check_update";
	public static final String API_UPLOAD_DATA = "/api/group_des2/bak_msg";
	public static final String API_DELETE = "/api/group_des2/memberdelete";
	public static final String API_UID_IN_GROUP = "/api/meeting_des2/uidingroup";
	public static final String API_UPDATE_SHOWNAME = "/api/meeting_des2/updateshowname";
	public static final String API_UPDATE_GROUPNAME = "/api/meeting_des2/updategroupname";
	public static final String API_GET_IMAGE = "/api/avatar_des2/avatar/";
	public static final String API_SET_EMAIL = "/api/user_des2/set_email";
	public static final String API_SET_DEFAULT_NICKNAME = "/api/user_des2/ChangeUserName";
	public static final String API_CHANGE_STATES = "/api/meeting_des2/change_status";
	public static final String API_IS_REGISTER = "/api/user_des2/user_exists";
	public static final String API_SEND_TO_SMS = "/api/user_des2/send_xsms";
	public static final String API_START_BLUETOOTH_SIGN = "/api/meeting_des2/bluetooth_meetingadd_v1";
	public static final String API_UPLOAD_BLUETOOTH_INFO = "/api/meeting_des2/upload_bluetooth_info2";
	public static final String API_GET_BLUETOOTH_INFO = "/api/meeting_des2/bluetooth_info?p={\"group_id\":\"%s\"}";
	public static final String API_IS_BLUETOOTH_SIGNED = "/api/meeting_des2/bluetooth_issign?p={\"group_id\":\"%s\"}";
	public static final String API_BLUETOOTH_JOIN_SIGN = "/api/meeting_des2/bluetooth_sign";
	public static final String API_BLUETOOTH_STOP_SIGN = "/api/meeting_des2/bluetooth_end_meeting";
	public static final String API_UPLOAD_MYSELF_BLUETOOTH_DATA = "/api/meeting_des2/my_bluetoothinfo";
	public static final String API_PERSONAL_PAGE_TO_PERSONAL_MEETINGLIST = "/api/meeting_des2/paging_historymeetinglist_personal_v1";
	public static final String API_GROUP_PAGE_TO_PERSONAL_MEETINGLIST = "/api/meeting_des2/paging_historymeetinglist_group_bytime";
	public static final String API_IS_SIGNING = "/api/meeting_des2/issign_v1";
	public static final String API_CHANGE_USERINFO = "/api/user_des2/change_userinfo";
	public static final String API_MY_SIGNRECORD = "/api/meeting_des2/my_signrec";
	public static final String API_IS_CAN_JOIN_GROUP = "/api/group_des2/set_groupentrance";
	public static final String API_MEETING_COUNT_DETAIL = "/api/meeting_des2/check_meeting_detail";
	public static final String API_ABSENT_DETAILS = "/api/meeting_des2/check_absent_details";
	public static final String API_FEED_BACK = "/api/feedback_des2/send_feed";
	public static final String API_EXPORT_MEETINGREC_BYTIME = "/api/meeting_des2/export_meetingrec_bytime";
}