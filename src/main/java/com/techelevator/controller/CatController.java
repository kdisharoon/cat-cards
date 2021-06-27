package com.techelevator.controller;

import com.techelevator.dao.CatCardDao;
import com.techelevator.model.CatCard;
import com.techelevator.model.CatCardNotFoundException;
import com.techelevator.services.CatFactService;
import com.techelevator.services.CatPicService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
    public CatCard getNewCard() {
        CatCard c = new CatCard();

        String fact = catFactService.getFact().getFact();
        String imgUrl = catPicService.getPic().getFile();

        c.setCatFact(fact);
        c.setImgUrl(imgUrl);

        return c;
    }

    @RequestMapping(path = "/api/cards", method = RequestMethod.GET)
    public List<CatCard> getAllCards() {
        return catCardDao.list();
    }

    @RequestMapping(path = "/api/cards/{id}", method = RequestMethod.GET)
    public CatCard getCard(@PathVariable long id) throws CatCardNotFoundException {
        return catCardDao.get(id);
    }

    @RequestMapping(path = "/api/cards", method = RequestMethod.POST)
    public boolean saveCard(@RequestBody CatCard c) {
        catCardDao.save(c);
        return true;
    }

    @RequestMapping(path = "/api/cards/{id}", method = RequestMethod.PUT)
    public boolean updateCard(@PathVariable long id, @RequestBody CatCard c) throws CatCardNotFoundException {
        catCardDao.update(id, c);
        return true;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/api/cards/{id}", method = RequestMethod.DELETE)
    public boolean deleteCard(@PathVariable long id) throws CatCardNotFoundException {
        catCardDao.delete(id);
        return true;
    }

}
