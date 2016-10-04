using Org.Naima.Services.Cases.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Http;
using System.Web.OData;

namespace cases.net.Controllers
{
    public class CareProvidersController : ODataController
    {
        CasesContext db = new CasesContext();

        protected override void Dispose(bool disposing)
        {
            db.Dispose();
            base.Dispose(disposing);
        }
        [EnableQuery]
        public IQueryable<CareProvider> Get()
        {
            return db.CareProviders;
        }
        [EnableQuery]
        public SingleResult<Location> GetLocation([FromODataUri]int key)
        {
            var result = db.CareProviders.Where(l => l.Id == key).Select(cp => cp.Location);
            return SingleResult.Create<Location>(result);
        }
        
        [EnableQuery]
        public IQueryable<Case> GetCases([FromODataUri] int key)
        {
            return db.CareProviders.Where(m => m.Id.Equals(key)).SelectMany(m => m.Cases);
        }
        public async Task<IHttpActionResult> Post(CareProvider cp)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }
            db.CareProviders.Add(cp);
            await db.SaveChangesAsync();
            return Created(cp);
        }
    }
}
