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



public Money calculatePay(Employee e) throws InvalidEmployeeType {
    switch (e.type) {
       case COMMISSIONED:
         return calculateCommissionedPay(e);
       case HOURLY:
         return calculateHourlyPay(e);
       case SALARIED:
         return calculateSalariedPay(e);
       default:
         throw new InvalidEmployeeType(e.type);
    }
}


