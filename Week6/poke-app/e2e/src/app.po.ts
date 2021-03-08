import { browser, by, element } from 'protractor';

export class AppPage {
  async navigateTo(): Promise<unknown> {
    return browser.get(browser.baseUrl);
  }

  // async getTitleText(): Promise<string> {
  //   return element(by.css('app-root .content span')).getText();
  // }

  async getPokemonHeader(): Promise<string> {
    return element(by.id('pokemonHeader')).getText();
  }

  async getPokemon() {
    element(by.id('getBtn')).click();
  }

  async inputPikachuId() {
    element(by.id('idInput')).sendKeys('25');
  }
}
