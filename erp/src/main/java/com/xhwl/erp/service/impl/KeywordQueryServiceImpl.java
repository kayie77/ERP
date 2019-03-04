package com.xhwl.erp.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xhwl.erp.domain.BusinessCategoryRepository;
import com.xhwl.erp.domain.BusinessRepository;
import com.xhwl.erp.domain.ClientRepository;
import com.xhwl.erp.domain.ContractBillingRepository;
import com.xhwl.erp.domain.ContractInfoRepository;
import com.xhwl.erp.domain.InboundListRepository;
import com.xhwl.erp.domain.MaterialCategoryRepository;
import com.xhwl.erp.domain.OutboundListRepository;
import com.xhwl.erp.domain.PaymentContractRepository;
import com.xhwl.erp.domain.PriceRepository;
import com.xhwl.erp.domain.ProjectRepository;
import com.xhwl.erp.domain.RegionRepository;
import com.xhwl.erp.domain.SupplyRegionRepository;
import com.xhwl.erp.domain.SupplyRepository;
import com.xhwl.erp.domain.UserRepository;
import com.xhwl.erp.entity.BusinessCategory;
import com.xhwl.erp.entity.MaterialCategory;
import com.xhwl.erp.entity.Region;
import com.xhwl.erp.entity.SupplyRegion;
import com.xhwl.erp.service.CityService;
import com.xhwl.erp.service.KeywordQueryService;
import com.xhwl.erp.util.tree.ETreeNode;
import com.xhwl.erp.util.util.Constants;

@Service
public class KeywordQueryServiceImpl implements KeywordQueryService{
	
	@Autowired
	private BusinessRepository businessRepository;
	
	@Autowired
	private RegionRepository regionRepository;
	
	@Autowired 
	private CityService cityService;
	
	@Autowired 
	private ClientRepository clientRepository;
	
	@Autowired 
	private BusinessCategoryRepository businessCtgRepository;
	
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private ContractInfoRepository contractInfoRepository;
	
	@Autowired
	private ContractBillingRepository contractBillingRepository;
	
	@Autowired
	private PaymentContractRepository paymentContractRepository;
	
	@Autowired
	private InboundListRepository inboundListRepository;
	
	@Autowired
	private OutboundListRepository outboundListRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private SupplyRepository supplyRepository;
	
	@Autowired
	private SupplyRegionRepository supplyRegionRepository;
	
	@Autowired
	private MaterialCategoryRepository materialCtgRepository;
	
	@Autowired
	private PriceRepository priceRepository;
	
	@Override
	public JSONObject clientName(String clientName) {
		JSONObject jsonobject = new JSONObject();
		List<Object> objectList  = new ArrayList<>();
		List<Object[]> clientNameList = clientRepository.getNameArrary(clientName);
		for (Object[] obj :  clientNameList) {
			Number id =0;
			String name=null,category=null,phone=null;
			JSONObject returnData = new JSONObject();
			Object[] objArrary = (Object[]) obj;
			id = (Number) objArrary[0];
			name = (String) objArrary[1];
			category = (String) objArrary[2];
			phone = (String) objArrary[3];
			returnData.put("id", id);
			returnData.put("name", name);
			returnData.put("category", category);
			returnData.put("phone", phone);
			objectList.add(returnData);
		}
		jsonobject.put("objectList", objectList);
		return jsonobject;
	}

	@Override
	public JSONObject userName(String userName) {
		JSONObject jsonobject = new JSONObject();
		List<Object> objectList  = new ArrayList<>();
		List<Object[]> userNameList = userRepository.getNameArrary(userName);
		for (Object[] obj :  userNameList) {
			Number id =0;
			String name=null,code=null,phone=null;
			JSONObject returnData = new JSONObject();
			Object[] objArrary = (Object[]) obj;
			id = (Number) objArrary[0];
			code = (String) objArrary[1];
			name = (String) objArrary[2];
			phone = (String) objArrary[3];
			returnData.put("id", id);
			returnData.put("code", code);
			returnData.put("name", name);
			returnData.put("phone", phone);
			objectList.add(returnData);
		}
		jsonobject.put("objectList", objectList);
		return jsonobject;
	}
	
	@Override
	public JSONObject businessName(String role_code,String businessName) {
		JSONObject jsonobject = new JSONObject();
		List<Object[]> businessNameList = null;
		List<Object> objectList = new ArrayList<>();
		if(role_code !=null) {
			if( role_code.substring(0, 3).equals(Constants.ROLE_CODE)) {
				businessNameList = businessRepository.getNameArrary(businessName,role_code.substring(0, 4));
			}else {
				businessNameList = businessRepository.getNameArrary(businessName);
			}
		}
		for (Object[] obj :  businessNameList) {
			Number id =0;
			String code=null,name=null,clientName=null,businessCtg=null,clientCtg=null,cityName=null,regionName=null;
			double amount;
			JSONObject returnData = new JSONObject();
			JSONObject object = new JSONObject();
			Object[] objArrary = (Object[]) obj;
			id = (Number) objArrary[0];
			code = (String) objArrary[1];
			name = (String) objArrary[2];
			clientName = (String) objArrary[3];//客户名称
			amount = (double) objArrary[4];
			businessCtg = (String) objArrary[5];//业务类别
			clientCtg = (String) objArrary[6];//客户类别
			cityName = (String) objArrary[7];//城市
			regionName = (String) objArrary[8];//区域
			
			returnData.put("id", id);
			returnData.put("name", name);
			returnData.put("code", code);
			returnData.put("clientName", clientName);
			returnData.put("amount", amount);
			object.put("name", businessCtg);
			returnData.put("businessCtg", object);
			returnData.put("clientCtg", clientCtg);
			returnData.put("cityName", cityName);
			returnData.put("regionName", regionName);
			objectList.add(returnData);
		}
		
		jsonobject.put("objectList", objectList);
		return jsonobject;
	}
	
	@Override
	public JSONObject businessCode(String role_code,String businessCode) {
		JSONObject jsonobject = new JSONObject();
		List<Object[]> businessCodeList = null;
		List<Object> objectList = new ArrayList<>();
		if(role_code !=null) {
			if( role_code.substring(0, 3).equals(Constants.ROLE_CODE)) {
				businessCodeList = businessRepository.getCodeArrary(businessCode,role_code.substring(0, 4));
			}else {
				businessCodeList = businessRepository.getCodeArrary(businessCode);
			}
		}
		for (Object[] obj :  businessCodeList) {
			Number id =0;
			String code=null,name=null,clientName=null,businessCtg=null,clientCtg=null,cityName=null,regionName=null;
			double amount;
			JSONObject object = new JSONObject();
			JSONObject returnData = new JSONObject();
			Object[] objArrary = (Object[]) obj;
			id = (Number) objArrary[0];
			code = (String) objArrary[1];
			name = (String) objArrary[2];
			clientName = (String) objArrary[3];//客户名称
			amount = (double) objArrary[4];
			businessCtg = (String) objArrary[5];//业务类别
			clientCtg = (String) objArrary[6];//客户类别
			cityName = (String) objArrary[7];//城市
			regionName = (String) objArrary[8];//区域
			
			returnData.put("id", id);
			returnData.put("name", name);
			returnData.put("code", code);
			returnData.put("clientName", clientName);
			returnData.put("amount", amount);
			object.put("name", businessCtg);
			returnData.put("businessCtg", object);
			returnData.put("clientCtg", clientCtg);
			returnData.put("cityName", cityName);
			returnData.put("regionName", regionName);
			objectList.add(returnData);
		}
		
		jsonobject.put("objectList", objectList);
		return jsonobject;
	}
	
	@Override
	public JSONObject contractInfoName(String role_code,String contractInfoName) {
		JSONObject jsonobject = new JSONObject();
		List<Object[]>  contractInfoNameList = null;
		List<Object> objectList = new ArrayList<>();
		if(role_code !=null) {
			if( role_code.substring(0, 3).equals(Constants.ROLE_CODE)) {
				 contractInfoNameList =  contractInfoRepository.getNameArrary( contractInfoName,role_code.substring(0, 4));
			}else {
				 contractInfoNameList =  contractInfoRepository.getNameArrary( contractInfoName);
			}
		}
		for (Object[] obj :   contractInfoNameList) {
			Number id =0;
			String code=null,name=null,businessCtg=null;
			JSONObject object = new JSONObject();
			JSONObject object1 = new JSONObject();
			JSONObject returnData = new JSONObject();
			Object[] objArrary = (Object[]) obj;
			id = (Number) objArrary[0];
			code = (String) objArrary[1];
			name = (String) objArrary[2];
			businessCtg = (String) objArrary[3];
			returnData.put("id", id);
			returnData.put("name", name);
			returnData.put("code", code);
			object.put("name", businessCtg);
			object1.put("businessCategory", object);
			returnData.put("business", object1);
			objectList.add(returnData);
		}
		
		jsonobject.put("objectList", objectList);
		return jsonobject;
	}
	
	@Override
	public JSONObject contractInfoCode(String role_code,String contractInfoCode) {
		JSONObject jsonobject = new JSONObject();
		List<Object[]>  contractInfoCodeList = null;
		List<Object> objectList = new ArrayList<>();
		if(role_code !=null) {
			if( role_code.substring(0, 3).equals(Constants.ROLE_CODE)) {
				 contractInfoCodeList =  contractInfoRepository.getCodeArrary( contractInfoCode,role_code.substring(0, 4));
			}else {
				 contractInfoCodeList =  contractInfoRepository.getCodeArrary( contractInfoCode);
			}
		}
		for (Object[] obj : contractInfoCodeList) {
			Number id =0;
			String code=null,name=null,businessCtg=null;
			JSONObject object = new JSONObject();
			JSONObject object1 = new JSONObject();
			JSONObject returnData = new JSONObject();
			Object[] objArrary = (Object[]) obj;
			id = (Number) objArrary[0];
			code = (String) objArrary[1];
			name = (String) objArrary[2];
			businessCtg = (String) objArrary[3];
			returnData.put("id", id);
			returnData.put("code", code);
			returnData.put("name", name);
			object.put("name", businessCtg);
			object1.put("businessCategory", object);
			returnData.put("business", object1);
			objectList.add(returnData);
		}
		
		jsonobject.put("objectList", objectList);
		return jsonobject;
	}
	
	@Override
	public JSONObject contractBillingNumber(String role_code,String contractBillingNumber) {
		JSONObject jsonobject = new JSONObject();
		List<Object[]>  contractBillingList = null;
		List<Object> objectList = new ArrayList<>();
		if(role_code !=null) {
			if( role_code.substring(0, 3).equals(Constants.ROLE_CODE)) {
				contractBillingList =  contractBillingRepository.getNumberArrary(contractBillingNumber,role_code.substring(0, 4));
			}else {
				contractBillingList =  contractBillingRepository.getNumberArrary(contractBillingNumber);
			}
		}
		for (Object[] obj : contractBillingList) {
			Number id =0;
			String number=null,name=null;
			
			JSONObject returnData = new JSONObject();
			Object[] objArrary = (Object[]) obj;
			id = (Number) objArrary[0];
			number = (String) objArrary[1];
			name = (String) objArrary[2];
			returnData.put("id", id);
			returnData.put("number", number);
			returnData.put("name", name);
			objectList.add(returnData);
		}
		
		jsonobject.put("objectList", objectList);
		return jsonobject;
	}
	
	@Override
	public JSONObject orderCode(String role_code,String orderCode) {
		JSONObject jsonobject = new JSONObject();
		List<Object[]> orderCodeList = null;
		List<Object> objectList = new ArrayList<>();
		if(role_code !=null) {
			if( role_code.substring(0, 3).equals(Constants.ROLE_CODE)) {
				orderCodeList =  paymentContractRepository.getOrderCodeArrary(orderCode,role_code.substring(0, 4));
			}else {
				orderCodeList =  paymentContractRepository.getOrderCodeArrary(orderCode);
			}
		}
		for (Object[] obj : orderCodeList) {
			Number id =0;
			String code=null;
			
			JSONObject returnData = new JSONObject();
			Object[] objArrary = (Object[]) obj;
			id = (Number) objArrary[0];
			code = (String) objArrary[1];
			returnData.put("id", id);
			returnData.put("code", code);
			objectList.add(returnData);
		}
		
		jsonobject.put("objectList", objectList);
		return jsonobject;
	}
	
	@Override
	public JSONObject paymentContractCode(String role_code,String paymentContractCode) {
		JSONObject jsonobject = new JSONObject();
		List<Object[]> paymentContractCodeList = null;
		List<Object> objectList = new ArrayList<>();
		if(role_code !=null) {
			if( role_code.substring(0, 3).equals(Constants.ROLE_CODE)) {
				paymentContractCodeList =  paymentContractRepository.getCodeArrary(paymentContractCode,role_code.substring(0, 4));
			}else {
				paymentContractCodeList =  paymentContractRepository.getCodeArrary(paymentContractCode);
			}
		}
		for (Object[] obj : paymentContractCodeList) {
			Number id =0;
			String code=null;
			
			JSONObject returnData = new JSONObject();
			Object[] objArrary = (Object[]) obj;
			id = (Number) objArrary[0];
			code = (String) objArrary[1];
			returnData.put("id", id);
			returnData.put("code", code);
			objectList.add(returnData);
		}
		
		jsonobject.put("objectList", objectList);
		return jsonobject;
	}
	
	@Override
	public JSONObject inboundListCode(String role_code,String inboundListCode) {
		JSONObject jsonobject = new JSONObject();
		List<Object[]> inboundListCodeList = null;
		List<Object> objectList = new ArrayList<>();
		if(role_code !=null) {
			if( role_code.substring(0, 3).equals(Constants.ROLE_CODE)) {
				inboundListCodeList =  inboundListRepository.getCodeArrary(inboundListCode,role_code.substring(0, 4));
			}else {
				inboundListCodeList =  inboundListRepository.getCodeArrary(inboundListCode);
			}
		}
		for (Object[] obj : inboundListCodeList) {
			Number id =0;
			String code=null,contractinfoName=null,regionName=null;
			JSONObject returnData = new JSONObject();
			Object[] objArrary = (Object[]) obj;
			id = (Number) objArrary[0];
			code = (String) objArrary[1];
			contractinfoName = (String) objArrary[2];
			regionName = (String) objArrary[3];
			returnData.put("id", id);
			returnData.put("code", code);
			returnData.put("contractinfoName", contractinfoName);
			returnData.put("regionName", regionName);
			objectList.add(returnData);
		}
		
		jsonobject.put("objectList", objectList);
		return jsonobject;
	}
	
	@Override
	public JSONObject outboundListCode(String role_code,String outboundListCode) {
		JSONObject jsonobject = new JSONObject();
		List<Object[]> outboundListCodeList = null;
		List<Object> objectList = new ArrayList<>();
		if(role_code !=null) {
			if( role_code.substring(0, 3).equals(Constants.ROLE_CODE)) {
				outboundListCodeList =  outboundListRepository.getCodeArrary(outboundListCode,role_code.substring(0, 4));
			}else {
				outboundListCodeList =  outboundListRepository.getCodeArrary(outboundListCode);
			}
		}
		for (Object[] obj : outboundListCodeList) {
			Number id =0;
			String code=null;
			JSONObject returnData = new JSONObject();
			Object[] objArrary = (Object[]) obj;
			id = (Number) objArrary[0];
			code = (String) objArrary[1];
			returnData.put("id", id);
			returnData.put("code", code);
			objectList.add(returnData);
		}
		
		jsonobject.put("objectList", objectList);
		return jsonobject;
	}
	
	@Override
	public JSONObject projectName(String role_code,String projectName) {
		JSONObject jsonobject = new JSONObject();
		List<Object[]> projectNameList = null;
		List<Object> objectList = new ArrayList<>();
		if(role_code !=null) {
			if( role_code.substring(0, 3).equals(Constants.ROLE_CODE)) {
				projectNameList =  projectRepository.getNameArrary(projectName,role_code.substring(0, 4));
			}else {
				projectNameList =  projectRepository.getNameArrary(projectName);
			}
		}
		for (Object[] obj : projectNameList) {
			Number id =0;
			String name=null;
			
			JSONObject returnData = new JSONObject();
			Object[] objArrary = (Object[]) obj;
			id = (Number) objArrary[0];
			name = (String) objArrary[1];
			returnData.put("id", id);
			returnData.put("name", name);
			objectList.add(returnData);
		}
		
		jsonobject.put("objectList", objectList);
		return jsonobject;
	}
	
	@Override
	public JSONObject supplyName(String supplyName) {
		JSONObject jsonobject = new JSONObject();
		List<Object> objectList  = new ArrayList<>();
		List<Object[]> supplyNameList = supplyRepository.getNameArrary(supplyName);
		for (Object[] obj :  supplyNameList) {
			Number id =0;
			String name=null,type=null, supplyRegion=null, supplyCycle=null;
			JSONObject object = new JSONObject();
			JSONObject returnData = new JSONObject();
			Object[] objArrary = (Object[]) obj;
			id = (Number) objArrary[0];
			name = (String) objArrary[1];
			type = (String) objArrary[2];
			supplyRegion = (String) objArrary[3];
			supplyCycle = (String) objArrary[4];
			returnData.put("id", id);
			returnData.put("name", name);
			returnData.put("type", type);
			object.put("name", supplyRegion);
			returnData.put("supplyRegion",object);
			returnData.put("supplyCycle", supplyCycle);
			objectList.add(returnData);
		}
		jsonobject.put("objectList", objectList);
		return jsonobject;
	}
	
	@Override
	public JSONObject priceName(String priceName) {
		JSONObject jsonobject = new JSONObject();
		List<Object> objectList  = new ArrayList<>();
		List<Object[]> priceNameList = priceRepository.getNameArrary(priceName);
		for (Object[] obj :  priceNameList) {
			Number id =0;
			String name=null,code=null;
			JSONObject returnData = new JSONObject();
			Object[] objArrary = (Object[]) obj;
			id = (Number) objArrary[0];
			code = (String) objArrary[1];
			name = (String) objArrary[2];
			returnData.put("id", id);
			returnData.put("code", code);
			returnData.put("name", name);
			objectList.add(returnData);
		}
		jsonobject.put("objectList", objectList);
		return jsonobject;
	}
	
	@Override
	public JSONObject priceCode(String priceCode) {
		JSONObject jsonobject = new JSONObject();
		List<Object> objectList  = new ArrayList<>();
		List<Object[]> priceCodeList = priceRepository.getCodeArrary(priceCode);
		for (Object[] obj :  priceCodeList) {
			Number id =0;
			String name=null,code=null;
			JSONObject returnData = new JSONObject();
			Object[] objArrary = (Object[]) obj;
			id = (Number) objArrary[0];
			code = (String) objArrary[1];
			name = (String) objArrary[2];
			returnData.put("id", id);
			returnData.put("code", code);
			returnData.put("name", name);
			objectList.add(returnData);
		}
		jsonobject.put("objectList", objectList);
		return jsonobject;
	}
	
	@Override
	public JSONObject materialCtg() {
		JSONObject jsonobject = new JSONObject();
		Iterable<MaterialCategory> objectList = materialCtgRepository.findAll();
		jsonobject.put("objectList", objectList);
		return jsonobject;
	}
	
	@Override
	public JSONObject supplyRegion() {
		JSONObject jsonobject = new JSONObject();
		Iterable<SupplyRegion> objectList = supplyRegionRepository.findAll();
		jsonobject.put("objectList", objectList);
		return jsonobject;
	}
	
	@Override
	public JSONObject region() {
		JSONObject jsonobject = new JSONObject();
		Iterable<Region> objectList = regionRepository.findAll();
		jsonobject.put("objectList", objectList);
		return jsonobject;
	}
	
	@Override
	public JSONObject businessCtg() {
		JSONObject jsonobject = new JSONObject();
		Iterable<BusinessCategory> objectList = businessCtgRepository.findAll();
		jsonobject.put("objectList", objectList);
		return jsonobject;
	}
	
	@Override
	public JSONObject city() {
		JSONObject jsonobject = new JSONObject();
		List<ETreeNode> objectList = cityService.tree();
		jsonobject.put("objectList", objectList);
		return jsonobject;
	}
}
