
/* ConvertUtil.class */

public class ConvertUtil {

  private static final Log LOG = LogFactory.getLog(ConvertUtil.calss);


  // reflection
   public static Map<String, Object> convertObjectToMap(Object obj) throws Exception {
    Map<String, Object> map = new HashMap<>();
    Field[] fields = obj.getClass().getDeclaredFields();
    for (int i = 0; i < fields.length; i++) {
      fields[i].setAccessible(true);
      try {
        map.put(fields[i].getName(), fields[i].get(obj));
      } catch (Exception e) {
        throw e;
      } 
    } 
    return map;
  }
  
  //Map을 Object로 변환
  public static Object convertMapToObject(Map<String, Object> map, Object obj) {
   
    String keyAttribute = null;
    String setMethodString = "set";
    String methodString = null;
   
    Iterator<String> itr = map.keySet().iterator();
   
    while (itr.hasNext()) {
      keyAttribute = itr.next();
      methodString = setMethodString + toCamelCase(keyAttribute).substring(0, 1).toUpperCase() + toCamelCase(keyAttribute).substring(1);
      Method[] methods = obj.getClass().getDeclaredMethods();
      for (int i = 0; i < methods.length; i++) {
        if (methodString.equals(methods[i].getName()))
          try {
            Class<?>[] parameters = methods[i].getParameterTypes();
            if (parameters.length == 1 && parameters[0] == Double.class) {
              methods[i].invoke(obj, new Object[] { Double.valueOf((String)map.get(keyAttribute)) });
            } else if (parameters.length == 1 && parameters[0] == String.class) {
              methods[i].invoke(obj, new Object[] { map.get(keyAttribute) });
            } else {
              methods[i].invoke(obj, new Object[] { map.get(keyAttribute) });
            } 
          } catch (Exception e) {
            LOG.error("vo convert error");
            LOG.error(e);
          }  
      } 
    } 
    return obj;
  }
  
  //camelCase로 변환
  public static String toCamelCase(String target) {
    StringBuffer buffer = new StringBuffer();
    for (String token : target.toLowerCase().split("_"))
      buffer.append(StringUtils.capitalize(token)); 
    return StringUtils.uncapitalize(buffer.toString());
  }
  
  //queryString 변환
  public static String convertMapToQueryString(Map<String, Object> paramMap) {
    List<NameValuePair> params = Lists.newArrayList();
    if (paramMap != null)
      for (Map.Entry<String, Object> paramEntry : paramMap.entrySet()) {
        Object value = paramEntry.getValue();
        if (value != null)
          params.add(new BasicNameValuePair(paramEntry.getKey(), value.toString())); 
      }  
    return URLEncodedUtils.format(params, "UTF-8");
  }
  

  //map으로 변환 
  public static Map<String, Object> convertObjectToMapByMapper(Object obj) {

    //Type 안정성
    TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {};

    ObjectMapper objectMapper = new ObjectMapper();
    Map<String, Object> map = (Map<String, Object>)objectMapper.convertValue(obj, typeRef);
    return map;

  } 


}






