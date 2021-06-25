package com.techelevator.controller;

import com.techelevator.dao.CatCardDao;
import com.techelevator.model.CatCard;
import com.techelevator.model.CatFact;
import com.techelevator.model.CatPic;
import com.techelevator.services.CatFactService;
import com.techelevator.services.CatPicService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public class CatController {

    private CatCardDao catCardDao;
    private CatFactService catFactService;
    private CatPicService catPicService;

    public CatController(CatCardDao catCardDao, CatFactService catFactService, CatPicService catPicService) {
        this.catCardDao = catCardDao;
        this.catFactService = catFactService;
        this.catPicService = catPicService;
    }

    @RequestMapping(path = "/api/cards/random", method = RequestMethod.GET)
    public CatCard getRandomCard() {
        CatCard c = new CatCard();

        String fact = catFactService.getFact().getText();
        String imgUrl = catPicService.getPic().getFile();

        c.setCatFact(fact);
        c.setImgUrl(imgUrl);
        c.setCatCardId(makeRandomId());

        return c;
    }

    @RequestMapping(path = "/api/cards", method = RequestMethod.GET)
    public List<CatCard> getAllCards() {
        return catCardDao.list();
    }

    @RequestMapping(path = "/api/cards/{id}", method = RequestMethod.GET)
    public CatCard getCard(@PathVariable long id) {
        return catCardDao.get(id);
    }



    //Need POST, PUT, DELETE methods still



    private long makeRandomId() {
        int maxID = 0;
        for (CatCard c : catCardDao.list()) {
            if (c.getCatCardId() > maxID) {
                maxID = Math.toIntExact(c.getCatCardId());
            }
        }
        return maxID;
    }

}
