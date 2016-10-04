using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using Org.Naima.Services.Cases.Models;
using System.Web.OData;
using System.Threading.Tasks;

namespace cases.net.Controllers
{
    public class LocationsController : ODataController
    {
        CasesContext db = new CasesContext();

        protected override void Dispose(bool disposing)
        {
            db.Dispose();
            base.Dispose(disposing);
        }

        private bool LocationExists(int key)
        {
            return db.Locations.Any(p => p.Id == key);
        }
        [EnableQuery]
        public IQueryable<Location> Get()
        {
            return db.Locations;
        }
        public SingleResult<Location> GetLocation([FromODataUri]int key)
        {
            var result = db.Locations.Where(l => l.Id == key);
            return SingleResult.Create<Location>(result);
        }
        public async Task<IHttpActionResult> Post(Location location)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }
            db.Locations.Add(location);
            await db.SaveChangesAsync();
            return Created(location);
        }
    }
}
