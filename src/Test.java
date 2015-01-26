import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Test {
	
	public final static String sql_90014 ="select distinct t2.form_no form_no,t1.form_title form_title,t2.bss_customer_name bss_customer_name,t3.user_name user_name,t4.enum_value_meaning bizType,t5.enum_value_meaning customerType,t2.circuit_no ciruit_no,t2.Wire_Speed wire_speed,t2.WIRE_SPEED_UNIT wire_speed_unit,t2.PORTA_TYPE porta_type,t2.portb_type portb_type,t2.OTHER_RESOURCE other_resource,t2.TRANS_RESOURCE_ROUTE trans_resource_route,t2.trans_resource_circuit trans_resource_circuit,t2.Trans_Resource trans_resource,t2.switch_resource switch_resource,t2.DATA_RESOURCE data_resource,t2.Disform_Desc disform_desc,t2.BSS_PRODUCT_NO bss_product_no,t2.CUSTOMER_ADDRESS customer_address,t2.CUSTOMER_CONTACT customer_contact from t_form_main t1,T_FORM_LOCAL_DISPATCH  t2,info_person t3,T_APP_ENUM_VALUE t4,T_APP_ENUM_VALUE t5 where t2.parent_form_id=t1.pk_id and t1.send_user_id=t3.user_id and (t4.enum_item_code(+)='businessType' and t4.enum_value_num(+)=t2.biz_type) and(t5.enum_item_code(+)='customerType' and t5.enum_value_num(+)=t2.customer_type)";

	public static void main(String[] args) {
		
		
	
		    Calendar cal = Calendar.getInstance();
	        int year = cal.get(Calendar.YEAR);//获取年份
	        int month=cal.get(Calendar.MONTH);//获取月份
	        int day=cal.get(Calendar.DATE);//获取日
	        int hour=cal.get(Calendar.HOUR_OF_DAY);//小时
	        int minute=cal.get(Calendar.MINUTE);//分           
	        int second=cal.get(Calendar.SECOND);//秒 
	        
	        System.out.println(hour);
		
		
	}
}
