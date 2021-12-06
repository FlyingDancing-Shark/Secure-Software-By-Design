 public static String renderPageWithSetupsAndTeardowns(
                                      PageData pageData, boolean isSuite) throws Exception 
 {
      boolean isTestPage = pageData.hasAttribute("Test");
      if (isTestPage) {
          WikiPage testPage = pageData.getWikiPage();
          StringBuffer newPageContent = new StringBuffer();
          includeSetupPages(testPage, newPageContent, isSuite);
          newPageContent.append(pageData.getContent());
          includeTeardownPages(testPage, newPageContent, isSuite);
          pageData.setContent(newPageContent.toString());
      }
      return pageData.getHtml();
 }
 


public static String renderPageWithSetupsAndTeardowns(
                        PageData pageData, boolean isSuite) throws Exception {
   if (isTestPage(pageData))
      includeSetupAndTeardownPages(pageData, isSuite);
   return pageData.getHtml();
}


public abstract class Employee {
  public abstract boolean isPayday();
  public abstract Money calculatePay();
  public abstract void deliverPay(Money pay);
}


public interface EmployeeFactory {
  public Employee makeEmployee(EmployeeRecord r) throws InvalidEmployeeType;
}

public class EmployeeFactoryImpl implements EmployeeFactory {
 
   public Employee makeEmployee(EmployeeRecord r) throws InvalidEmployeeType {
     switch (r.type) {
       case COMMISSIONED:
          return new CommissionedEmployee(r) ;
       case HOURLY:
          return new HourlyEmployee(r);
       case SALARIED:
          return new SalariedEmploye(r);
      default:
         throw new InvalidEmployeeType(r.type);
     }
  }
 
}



