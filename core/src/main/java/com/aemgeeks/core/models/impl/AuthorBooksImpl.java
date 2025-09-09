package com.aemgeeks.core.models.impl;

import com.aemgeeks.core.helper.MultifieldHelper;
import com.aemgeeks.core.helper.NastedHelper;
import com.aemgeeks.core.models.AuthorBooks;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.*;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class},
       adapters = AuthorBooks.class,
       defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class AuthorBooksImpl implements AuthorBooks {
    private static final Logger LOG = LoggerFactory.getLogger(AuthorBooksImpl.class);

    @Inject
    Resource resource;

    @ValueMapValue
    private String authorname;

    @ValueMapValue
    List<String> books;

    @Override
    public String getAuthorName() {
        return authorname;
    }

    @Override
    public List<String> getAuthorBooks() {
        if(books!=null){
            return new ArrayList<String>(books);
        }else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Map<String, String>> getBookDetailsWithMap() {
        List<Map<String,String>> bookDetailsMap = new ArrayList<>();
        try {
            Resource bookDetail = resource.getChild("bookdetailswithmap");
            if (bookDetail!=null){
                for (Resource book : bookDetail.getChildren()){
                    Map<String,String> bookMap = new HashMap<>();
                    bookMap.put("bookname",book.getValueMap().get("bookname", String.class));
                    bookMap.put("booksubject",book.getValueMap().get("booksubject", String.class));
                    bookMap.put("publishyear",book.getValueMap().get("publishyear", String.class));
                    bookDetailsMap.add(bookMap);
                }
            }
        } catch (Exception e) {
            LOG.info("\n Error While Getting the Book Details : {} ",e.getMessage());
        }
        LOG.info("\n SIZE : {}",bookDetailsMap.size());
        return bookDetailsMap;
    }

    @Override
    public List<MultifieldHelper> getBookDetailsWithBean() {
        List<MultifieldHelper> bookDetailsBean = new ArrayList<>();
        try {
            Resource bookDetailBean = resource.getChild("bookdetailswithbean");
            if (bookDetailBean!=null){
                for (Resource bookBean : bookDetailBean.getChildren()){
                    LOG.info("\n BEAN PATH : {}",bookBean.getPath());
                    LOG.info("\n BEAN PRO : {}",bookBean.getValueMap().get("bookname", String.class));
                    bookDetailsBean.add(new MultifieldHelper(bookBean));
                }
            }
        } catch (Exception e) {
            LOG.info("\n BEAN ERROR : {}",e.getMessage());
        }
        return bookDetailsBean;
    }

    @Override
    public List<MultifieldHelper> getBookDetailsWithNastedMultifield() {
        List<MultifieldHelper> bookDetailsNasted = new ArrayList<>();
        try {
            Resource bookDetailNasted = resource.getChild("bookdetailswithnastedmultifield");
            if (bookDetailNasted!=null){
                for (Resource bookNasted : bookDetailNasted.getChildren()){
                    MultifieldHelper multifieldHelper = new MultifieldHelper(bookNasted);
                    if (bookNasted.hasChildren()){
                        List<NastedHelper> bookNastedList = new ArrayList<>();
                        Resource nastedResource = bookNasted.getChild("bookeditions");
                        for(Resource nasted : nastedResource.getChildren()){
                              bookNastedList.add(new NastedHelper(nasted));
                        }
                        multifieldHelper.setBookEditions(bookNastedList);
                    }
                    bookDetailsNasted.add(multifieldHelper);
                }
            }
        } catch (Exception e) {
            LOG.info("\n Error While getting the Books With Nasted Multifield Helper : {}",e.getMessage());
        }
        return bookDetailsNasted;
    }

}
