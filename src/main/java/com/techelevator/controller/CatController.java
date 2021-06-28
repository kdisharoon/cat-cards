package com.techelevator.controller;

import com.techelevator.dao.CatCardDao;
import com.techelevator.model.CatCard;
import com.techelevator.model.CatCardNotFoundException;
import com.techelevator.services.CatFactService;
import com.techelevator.services.CatPicService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RequestMapping("/api/cards")
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

    @RequestMapping(path = "/random", method = RequestMethod.GET)
    public CatCard getNewCard() {
        CatCard c = new CatCard();

        String fact = catFactService.getFact().getFact();
        String imgUrl = catPicService.getPic().getFile();

        c.setCatFact(fact);
        c.setImgUrl(imgUrl);

        return c;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<CatCard> getAllCards() {
        return catCardDao.list();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public CatCard getCard(@PathVariable long id) throws CatCardNotFoundException {
        return catCardDao.get(id);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public CatCard saveCard(@RequestBody @Valid CatCard c) {
        catCardDao.save(c);
        return c;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public CatCard updateCard(@PathVariable @Valid long id, @RequestBody @Valid CatCard c) throws CatCardNotFoundException {
        catCardDao.update(id, c);
        return getCard(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteCard(@PathVariable long id) throws CatCardNotFoundException {
        catCardDao.delete(id);
        return true;
    }

}
