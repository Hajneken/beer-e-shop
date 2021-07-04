package at.electro.shop.auth.service.dao.persistence.models;

import java.io.Serializable;

public class CompositeKey implements Serializable {
  private Long accountId;
  private Long roleId;
}
